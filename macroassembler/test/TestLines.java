import fileaccess.*;
import lines.*;
import org.junit.jupiter.api.*;
import programs.Compiler;

import java.io.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLines {
    @org.junit.jupiter.api.Test
    void testCommentLine(TestReporter r) {
        LineLocation loc = new LineLocation(new File("src/nofile.txt"), 14);
        LineI cl = new CommentLine(loc, "# yay");
        // Single line tests
        try {
            assertEquals("# yay", cl.getSourceText());
            assertEquals("# yay", cl.getOutputText(new HashMap<>()));
            assertEquals(loc, cl.getLineLocation());
            assertNull(cl.getNextLine());
        } catch (NoSuchLabelException e) {
            fail("CommentLine should not look up labels");
        } catch (NoNextInstructionException e) {
            fail("CommentLine should not look up an address for the above calls");
        }
        try {
            cl.getAddress();
            fail("There is no instruction after this line.");
        } catch (NoNextInstructionException e) {
            // pass
        }

        // Tests that also involve an instruction
        try {
            InstrLine il = new InstrLine(new LineLocation(loc.getFile(), 15), 3, "write r1");
            cl.setNextLine(il);
            assertEquals(3, cl.getAddress());
            assertSame(il, cl.getNextLine());
        } catch (NoNextInstructionException e) {
            fail("An instruction has been added after.");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testLabelLine(TestReporter r) {
        LineLocation loc = new LineLocation(new File("src/nofile.txt"), 14);
        LineI ll = new LabelLine(loc, ":target:");
        // Single line tests
        try {
            assertEquals(":target:", ll.getSourceText());
            assertEquals("# :target:", ll.getOutputText(new HashMap<>()));
            assertEquals(loc, ll.getLineLocation());
            assertNull(ll.getNextLine());
        } catch (NoSuchLabelException e) {
            fail("LabelLine should not look up labels");
        } catch (NoNextInstructionException e) {
            fail("LabelLine should not look up an address for the above calls");
        }
        try {
            ll.getAddress();
            fail("There is no instruction after this line.");
        } catch (NoNextInstructionException e) {
            // pass
        }

        // Tests that also involve an instruction
        try {
            InstrLine il = new InstrLine(new LineLocation(loc.getFile(), 15), 3, "write r1");
            ll.setNextLine(il);
            assertEquals(3, ll.getAddress());
            assertSame(il, ll.getNextLine());
        } catch (NoNextInstructionException e) {
            fail("An instruction has been added after.");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }


    @org.junit.jupiter.api.Test
    void testInstrLine(TestReporter r) {
        LineLocation loc1 = new LineLocation(new File("src/nofile.txt"), 14);
        LineI ll = new LabelLine(loc1, ":target:");
        HashMap<String,LineI> lookup = new HashMap<>();
        lookup.put("target",ll);

        LineLocation loc2 = new LineLocation(new File("src/nofile.txt"), 15);
        LineI il = new InstrLine(loc2, 5, "halt");
        // Single line tests
        try {
            assertEquals("halt", il.getSourceText());
            assertEquals("5 halt", il.getOutputText(lookup));
            assertEquals(loc2, il.getLineLocation());
            assertNull(il.getNextLine());
        } catch (NoSuchLabelException e) {
            fail("InstrLine contains no labels");
        } catch (NoNextInstructionException e) {
            fail("InstrLine should hold it's address");
        }
        try {
            assertEquals(5,il.getAddress());
        } catch (NoNextInstructionException e) {
            fail("Instr lines have their own address");
        }

        // Tests that also involve a label
        try {
            ll.setNextLine(il);
            InstrLine il2 = new InstrLine(new LineLocation(loc1.getFile(), 13), 4, "jumpn :target:");
            il2.setNextLine(ll);
            assertSame(ll, il2.getNextLine());
            assertEquals("jumpn :target:", il2.getSourceText());
            assertEquals("4 jumpn 5 # :target:", il2.getOutputText(lookup));
            assertNull(il.getNextLine());
        } catch (NoNextInstructionException e) {
            fail("An instruction has been added after.");
        } catch (NoSuchLabelException e) {
            fail(":target: label is set in the lookup");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testIndirectLine(TestReporter r) {
        LineI[] lines = new LineI[100];
        File f = new File("nofile.txt");
        for(int i=0; i<99; i++) {
            LineLocation loc = new LineLocation(f, i+1);
            if(i%2==0) {
                lines[i] = new LabelLine(loc, ":lbl"+i+":");
            } else {
                lines[i] = new CommentLine(loc, "# Comment number "+i);
            }
        }
        lines[99] = new InstrLine(new LineLocation(f, 100), 17, "halt");
        for(int i=0; i<99; i++) {
            lines[i].setNextLine( lines[i+1] );
        }

        for(int i=0; i<99; i++) {
            assertSame(lines[i+1], lines[i].getNextLine());
        }
        try {
            for (int i = 0; i < 100; i++) {
                assertEquals(17, lines[i].getAddress());
            }
        } catch (NoNextInstructionException e) {
            fail("Sequence of lines has an instruction that should have been found.");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testBadFileTypes(TestReporter r) {
        File f = new File("data/badfilename.txt");
        f.deleteOnExit();
        try {
            FileWriter mkfile = new FileWriter(f);
            mkfile.write("halt");
        } catch (IOException e) {
            fail("Unit test is broken...");
        }

        try {
            HmmmWriterI fw = new HmmmWriter();
            fw.write(f, new HashMap<>(), new CommentLine(new LineLocation(f, 1), "# nothing to see here"));
            fail("File type should cause write method to fail");
        } catch (NoSuchLabelException e) {
            fail("No label lookup required");
        } catch (NoNextInstructionException e) {
            fail("No instruction lookup required");
        } catch (IOException e) {
            fail("File should be writeable");
        } catch (BadFileTypeException e) {
            // pass
        }
        try {
            LblReaderI fr = new LblReader();
            fr.read(f);
            fail("File type should cause read method to fail");
        } catch (DuplicateLabelException e) {
            fail("File contents should not require label lookup");
        } catch (FileNotFoundException e) {
            fail("File should exist and be readable");
        } catch (IOException e) {
            fail("File should be readable");
        } catch (BadFileTypeException e) {
            // pass
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testNoSuchLabel(TestReporter r) {
        File f = new File("data/test1.lbl");
        File f2 = new File("data/test1.hmmm");
        LblReaderI fr = new LblReader();
        HmmmWriterI fw = new HmmmWriter();
        try {
            fr.read(f);
            fw.write(f2, fr.getLabelLookup(), fr.getFirstLine());
        } catch (DuplicateLabelException e) {
            fail(".lbl file should be valid");
        } catch (NoSuchLabelException e) {
            fail(".lbl file should be valid");
        } catch (NoNextInstructionException e) {
            fail(".lbl file should be valid");
        } catch (FileNotFoundException e) {
            fail(".lbl file should be valid");
        } catch (NoLinesException e) {
            fail(".lbl file should be valid");
        } catch (IOException e) {
            fail(".lbl file should be valid");
        } catch (BadFileTypeException e) {
            fail(".lbl file should be vaild");
        }

        try {
            LineI l = new InstrLine(new LineLocation(f2,0), 1000, "jumpn :nowhere:");
            l.setNextLine( fr.getFirstLine() );
            fw.write(f2, fr.getLabelLookup(), l);
            fail("There in an instruction referencing an invalid label");
        } catch (NoSuchLabelException e) {
            // pass
        } catch (NoNextInstructionException e) {
            fail(".lbl file should be valid");
        } catch (FileNotFoundException e) {
            fail(".lbl file should be valid");
        } catch (NoLinesException e) {
            fail(".lbl file should be valid");
        } catch (IOException e) {
            fail(".lbl file should be valid");
        } catch (BadFileTypeException e) {
            fail(".lbl file should be vaild");
        }
        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testNoNextInstr(TestReporter r) {
        LineI[] lines = new LineI[100];
        File f = new File("nofile.txt");
        for(int i=0; i<100; i++) {
            LineLocation loc = new LineLocation(f, i+1);
            if(i%2==0) {
                lines[i] = new LabelLine(loc, ":lbl"+i+":");
            } else {
                lines[i] = new CommentLine(loc, "# Comment number "+i);
            }
        }
        for(int i=0; i<99; i++) {
            lines[i].setNextLine( lines[i+1] );
        }

        for(int i=0; i<100; i++) {
            try {
                lines[i].getAddress();
                fail("There is no instruction in these lines");
            } catch (NoNextInstructionException e) {
                // pass
            }
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testNoLines(TestReporter r) {
        LblReaderI fr = new LblReader();
        try {
            fr.getFirstLine();
            fail("No lines have been read, there is no correct first line");
        } catch (NoLinesException e) {
            // pass
        }
        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testEndToEnd(TestReporter r) {
        String[] args = new String[2];
        args[0] = "data/rec_fact.hmmm";
        args[1] = "data/rec_fact.lbl";

        // Run the program with the args
        Compiler.main(args);

        // TODO: Diff the files
        try {
            BufferedReader ref = new BufferedReader(new FileReader(new File("data/rec_fact_ref.hmmm")));
            BufferedReader out = new BufferedReader(new FileReader(new File("data/rec_fact.hmmm")));
            String refLine = ref.readLine();
            String outLine = out.readLine();
            while (refLine != null && outLine != null) {
                assertEquals(refLine, outLine);
                refLine = ref.readLine();
                outLine = out.readLine();
            }
            if (refLine == null && outLine != null) {
                fail("Output file has too many lines");
            }
            if (refLine != null && outLine == null) {
                fail("Output file is missing lines");
            }
        } catch (FileNotFoundException e) {
            fail(".lbl file should exist");
        } catch (IOException e) {
            fail(".lbl exists and directory should allow .hmmm to be written");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    /*
    @org.junit.jupiter.api.Test
    void template(TestReporter r) {
        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }
    */
}

