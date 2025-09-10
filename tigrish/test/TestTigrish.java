import fileaccess.AlreadySetException;
import fileaccess.SyntaxErrorException;
import fileaccess.TigrishReader;
import fileaccess.TigrishReaderI;
import org.junit.jupiter.api.*;
import runtime.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTigrish {
    @org.junit.jupiter.api.Test
    void testFileSecondSet(TestReporter r) {
        TigrishReaderI tr = new TigrishReader();
        try {
            tr.setFile(new File("data/push_constants.tgr"));
        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            fail("AlreadySetException on the first set call");
        }
        try {
            tr.setFile(new File("data/cube.tgr"));
            fail("File should not be able to be reset");
        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            // pass
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testIntegerReads(TestReporter r) {
        TigrishReaderI tr = new TigrishReader();
        try {
            tr.setFile(new File("data/push_constants.tgr"));
            // Read the comment
            Node n = tr.next();
            // Read the first int
            ValueI v = (ValueI)tr.next();
            assertEquals(15,v.getValue());
            v = (ValueI) tr.next();
            assertEquals(128, v.getValue());
            v = (ValueI) tr.next();
            assertEquals(42, v.getValue());
            v = (ValueI) tr.next();
            assertEquals(10, v.getValue());
            v = (ValueI) tr.next();
            assertEquals(543, v.getValue());
            v = (ValueI) tr.next();
            assertEquals(2000000, v.getValue());
            v = (ValueI) tr.next();
            assertEquals(8, v.getValue());
        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            fail("AlreadySetException on first call to setFile()");
        } catch (SyntaxErrorException e) {
            fail("No syntax errors in file");
        } catch (IOException e) {
            fail("There shouldn't be an IOException");
        } catch (ClassCastException e) {
            fail("Nodes representing integers should cast to ValueI");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }
    @org.junit.jupiter.api.Test
    void testIntegerEnv(TestReporter r) {
        TigrishReaderI tr = new TigrishReader();
        try {
            tr.setFile(new File("data/push_constants.tgr"));
            Environment env = new Environment();
            // Read the comment
            Node n;
            for(int i=0; i<8; i++) {
                n = tr.next();
                n.execute(env);
            }
            int[] vals = {8, 2000000, 543, 10, 42, 128, 15};
            for(int i=0; i<7; i++) {
                assertEquals(vals[i], ((ValueI)env.pop()).getValue());
            }
        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            fail("AlreadySetException on first call to setFile()");
        } catch (SyntaxErrorException e) {
            fail("No syntax errors in file");
        } catch (IOException e) {
            fail("There shouldn't be an IOException");
        } catch (ClassCastException e) {
            fail("Nodes representing integers should cast to ValueI");
        } catch (RuntimeFailureException e) {
            fail(".tgr file should execute cleanly");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testVarReads(TestReporter r) {
        TigrishReaderI tr = new TigrishReader();
        try {
            tr.setFile(new File("data/push_vars.tgr"));
            // Read the comment
            Node n = tr.next();
            // Read the first int
            n = tr.next();
            assertEquals("a",n.getText());
            n = tr.next();
            assertEquals("b",n.getText());
            n = tr.next();
            assertEquals("cat",n.getText());
            n = tr.next();
            assertEquals("dog",n.getText());
            n = tr.next();
            assertEquals("a_",n.getText());
            n = tr.next();
            assertEquals("thisIsALongName001",n.getText());
            n = tr.next();
            assertEquals("Rr__74__z",n.getText());
        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            fail("AlreadySetException on first call to setFile()");
        } catch (SyntaxErrorException e) {
            fail("No syntax errors in file");
        } catch (IOException e) {
            fail("There shouldn't be an IOException");
        } catch (ClassCastException e) {
            fail("Nodes representing integers should cast to ValueI");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }
    @org.junit.jupiter.api.Test
    void testVarEnv(TestReporter r) {
        TigrishReaderI tr = new TigrishReader();
        try {
            tr.setFile(new File("data/push_vars.tgr"));
            Environment env = new Environment();
            // Read the comment
            Node n;
            for(int i=0; i<8; i++) {
                n = tr.next();
                n.execute(env);
            }
            String[] vals = {"Rr__74__z","thisIsALongName001","a_","dog","cat","b","a"};
            for(int i=0; i<7; i++) {
                assertEquals(vals[i], env.pop().getText());
            }
        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            fail("AlreadySetException on first call to setFile()");
        } catch (SyntaxErrorException e) {
            fail("No syntax errors in file");
        } catch (IOException e) {
            fail("There shouldn't be an IOException");
        } catch (ClassCastException e) {
            fail("Nodes representing integers should cast to ValueI");
        } catch (RuntimeFailureException e) {
            fail(".tgr file should execute cleanly");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }
    @org.junit.jupiter.api.Test
    void testIntAssignment(TestReporter r) {
        TigrishReaderI tr = new TigrishReader();
        try {
            tr.setFile(new File("data/assignments.tgr"));
            Environment env = new Environment();
            int i=0;
            Node n = tr.next();
            while(n!=null && i<1000) {
                n.execute(env);
                n = tr.next();
                i++;
            }
            if(i==1000) {
                fail("reader appears to not reach the end of the file...");
            }
            ValueI v = env.fetch("a");
            assertEquals(15, v.getValue());
            v = env.fetch("d");
            assertEquals(4, v.getValue());
            v = env.fetch("b");
            assertEquals(2, v.getValue());
            v = env.fetch("c");
            assertEquals(3, v.getValue());
        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            fail("AlreadySetException on first call to setFile()");
        } catch (SyntaxErrorException e) {
            fail("No syntax errors in file");
        } catch (IOException e) {
            fail("There shouldn't be an IOException");
        } catch (ClassCastException e) {
            fail("Nodes representing integers should cast to ValueI");
        } catch (RuntimeFailureException e) {
            fail("Program should be valid");
        } catch (NoValueException e) {
            fail("variable should exist in the symboltable");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
}
    @org.junit.jupiter.api.Test
    void testIntMathOps(TestReporter r) {
        TigrishReaderI tr = new TigrishReader();
        try {
            tr.setFile(new File("data/intops.tgr"));
            Environment env = new Environment();

            // Read the comment
            Node n = tr.next();
            // Read the first int
            n = tr.next();
            for(int i=0;i<3;i++) {
                n.execute(env);
                n = tr.next();
            }
            // Top of stack should be the answer
            ValueI t = (ValueI) env.pop();
            assertEquals(9, t.getValue());

            // Next
            for(int i=0;i<3;i++) {
                n.execute(env);
                n = tr.next();
            }
            // Top of stack should be the answer
            t = (ValueI) env.pop();
            assertEquals(4, t.getValue());

            // Next
            for(int i=0;i<3;i++) {
                n.execute(env);
                n = tr.next();
            }
            // Top of stack should be the answer
            t = (ValueI) env.pop();
            assertEquals(2400, t.getValue());

            // Next
            for(int i=0;i<3;i++) {
                n.execute(env);
                n = tr.next();
            }
            // Top of stack should be the answer
            t = (ValueI) env.pop();
            assertEquals(5, t.getValue());

            // Next
            for(int i=0;i<3;i++) {
                n.execute(env);
                n = tr.next();
            }
            // Top of stack should be the answer
            t = (ValueI) env.pop();
            assertEquals(0, t.getValue());
        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            fail("AlreadySetException on first call to setFile()");
        } catch (SyntaxErrorException e) {
            fail("No syntax errors in file");
        } catch (IOException e) {
            fail("There shouldn't be an IOException");
        } catch (ClassCastException e) {
            fail("Nodes representing integers should cast to ValueI");
        } catch (RuntimeFailureException e) {
            fail("Program should be valid");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testVarAssignment(TestReporter r) {
        TigrishReaderI tr = new TigrishReader();
        try {
            tr.setFile(new File("data/varassgn.tgr"));
            Environment env = new Environment();
            // Get and execute the comment
            Node n = tr.next();
            // Get the 5, "five", and do the assignment
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            ValueI v = env.fetch("five");
            assertEquals(5, v.getValue());
            // Get the 6, "six", and to the assignment
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            v = env.fetch("five");
            assertEquals(5, v.getValue());
            v = env.fetch("six");
            assertEquals(6, v.getValue());
            // Get the five, six, add, eleven, and assignment
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            v = env.fetch("five");
            assertEquals(5, v.getValue());
            v = env.fetch("six");
            assertEquals(6, v.getValue());
            v = env.fetch("eleven");
            assertEquals(11, v.getValue());
            // Get the eleven, 10, *, six, and assignment
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            v = env.fetch("five");
            assertEquals(5, v.getValue());
            v = env.fetch("six");
            assertEquals(110, v.getValue());
            v = env.fetch("eleven");
            assertEquals(11, v.getValue());
            // five, six, +, eleven, =
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            v = env.fetch("five");
            assertEquals(5, v.getValue());
            v = env.fetch("six");
            assertEquals(110, v.getValue());
            v = env.fetch("eleven");
            assertEquals(115, v.getValue());
            // eleven, eleven, -, eleven, =
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            v = env.fetch("five");
            assertEquals(5, v.getValue());
            v = env.fetch("six");
            assertEquals(110, v.getValue());
            v = env.fetch("eleven");
            assertEquals(0, v.getValue());
            // five, six, =
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            v = env.fetch("five");
            assertEquals(5, v.getValue());
            v = env.fetch("six");
            assertEquals(5, v.getValue());
            v = env.fetch("eleven");
            assertEquals(0, v.getValue());
            //eleven, five, =
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            n = tr.next(); n.execute(env);
            v = env.fetch("five");
            assertEquals(0, v.getValue());
            v = env.fetch("six");
            assertEquals(5, v.getValue());
            v = env.fetch("eleven");
            assertEquals(0, v.getValue());
            // Get the final comments
            n = tr.next();
            n = tr.next();
            // Verify we hit the end of the file
            assertEquals(null, tr.next());

            tr = new TigrishReader();
            tr.setFile(new File("data/toThe8th.tgr"));
            env = new Environment();
            for(int i=0; i<18; i++) {
                n = tr.next();
                n.execute(env);
            }
            n = env.pop();
            assertEquals(1, n.getLocation().getLinenum());
            assertEquals(256, ((ValueI)n).getValue());
            env.push(n);
            n = tr.next();
            assertEquals(1, n.getLocation().getLinenum());
            n.execute(env);
            n = tr.next();
            assertEquals(2, n.getLocation().getLinenum());
            assertNull(tr.next());

        } catch (FileNotFoundException e) {
            fail("File should exist");
        } catch (AlreadySetException e) {
            fail("AlreadySetException on first call to setFile()");
        } catch (SyntaxErrorException e) {
            fail("No syntax errors in file");
        } catch (IOException e) {
            fail("There shouldn't be an IOException");
        } catch (ClassCastException e) {
            fail("Nodes representing integers should cast to ValueI");
        } catch (RuntimeFailureException e) {
            fail("Program should be valid");
        } catch (NoValueException e) {
            fail("variable should exist in the symboltable");
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
