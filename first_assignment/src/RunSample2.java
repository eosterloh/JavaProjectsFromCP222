import java.io.IOException;

/** Simple program to use Recall for seeing if a file contains some text
*/
public class RunSample2 {
    /** Calls printWithLineNums() on a specific file with specific search text
    */
    public static void main(String[] args) {
        // check that the program has been called correctly
        if(args.length!=2) {
            System.err.println("Incorrectly run, missing the file to display.");
            System.err.println("Usage: java RunSample2 <search word> <filename>");
            System.exit(1);
        }

        // This assignment only works if Recall implements RecallI
        RecallI r = new Recall();

        // Set the file
        r.setFilename(args[1]);

        // Generate the output
        try {
            if( null == r.findLineWith(args[0], true) ) {
                System.out.println("No line matches '"+args[0]+"' in "+args[1]);
            } else {
                System.out.println("Match is:");
                System.out.println(r.findLineWith(args[0], true));
            }
        } catch(UnsetFilenameException e) {
            // This should be impossible for this particular program
            // because setFilename() is always called with a string before
            // reaching this line
            System.err.println("This should never happen!");
            e.printStackTrace();
            System.exit(1);
        } catch(IOException e2) {
            // This might happen since any problem openning or reading the
            // file would cause an IOException
            System.out.println(args[1]+" could not be read...");
            System.out.println("Here's a cat picture instead:");
            System.out.println("               )\\._.,--....,'``.");
            System.out.println(" .b--.        /;   _.. \\   _\\  (`._ ,.");
            System.out.println("`=,-,-'~~~   `----(,_..'--(,_..'`-.;.'");
            System.exit(1);
        }
    }
}
