package programs;

import fileaccess.TigrishReader;
import fileaccess.TigrishReaderI;
import runtime.Environment;
import runtime.Node;

import java.io.File;

/** The Tigrish runtime */
public final class Tigrish {
    /** Can't instantiate one of these directly */
    private Tigrish() {}

    /** Runs a Tigrish program
     *  @param args args[0] is the filename to execute
     *  @throws Exception tigrish is for developers and can crash with stacktrace
     */
    public static void main(String[] args) throws Exception {
        // Check that there is a filename to work with
        if(args.length!=1) {
            System.out.println("Usage: java Tigrish <filename.tgr>");
            System.exit(1);
        }
        // Set the reader to read the file
        TigrishReaderI tr = new TigrishReader();
        tr.setFile(new File(args[0]));

        // Make the environment
        Environment env = new Environment();

        // Get the next Node until we are out of nodes
        Node n = tr.next();
        while(n!=null) {
            n.execute(env);
            n = tr.next();
        }

        // Let the user know that execution is complete
        System.out.println("Done");
    }
}
