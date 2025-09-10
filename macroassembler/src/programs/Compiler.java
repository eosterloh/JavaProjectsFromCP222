package programs;

import fileaccess.*;
import lines.DuplicateLabelException;
import lines.LineI;
import lines.NoNextInstructionException;
import lines.NoSuchLabelException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/** A .lbl to .hmmm compiler application that runs on the commandline */
public final class Compiler {
    /** Cannot construct Compiler instances */
    private Compiler() {}

    /** main method for the compiler
     *  <p>args[0] is the output filename, must end in .hmmm. args[1] is
     *  the input filename, must end in .lbl. Additional .lbl files may
     *  be given, each additional file at the next index args</p>
     *@param args output filename and input filenames
     */
    public static void main(String[] args) {
        if(args.length<2 || !args[0].endsWith(".hmmm")) {
            System.out.println("Usage: java Compiler <outfile.hmmm> <file1.lbl> ...");
            System.exit(1);
        }
        for(int i=1; i<args.length; i++) {
            if(!args[i].endsWith(".lbl")) {
                System.out.println("Usage: java Compiler <outfile.hmmm> <file1.lbl> ...");
                System.exit(1);
            }
        }

        try {
            // Read in all the source files
            LblReaderI reader = new LblReader();
            LineI firstLine;
            for (int i = 1; i < args.length; i++) {
                File f = new File(args[i]);
                reader.read(f);
            }

            // Write out the hmmm code
            HmmmWriterI writer = new HmmmWriter();
            File f = new File(args[0]);
            writer.write(f, reader.getLabelLookup(), reader.getFirstLine());
        } catch (DuplicateLabelException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Unrecoverable IO error. Underlying file access failed.");
            System.exit(1);
        } catch (BadFileTypeException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (NoLinesException e) {
            System.err.println("There were no source lines in the source file. Exiting.");
            System.exit(1);
        } catch (NoSuchLabelException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (NoNextInstructionException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
