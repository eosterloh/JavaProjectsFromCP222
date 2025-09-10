import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * A simple program to test the implementation with
 */
public class Main {
    /**
     * Since this is a simple main exclusively for testing, it is written
     * to allow exceptions to crash the progrom... For regular user
     * programs, all exceptions should be handled without crashing.
     * @param args the file from the commandline is at index 0
     * @throws NotASCIIException
     * @throws InvalidLineException
     * @throws IOException
     */
    public static void main(String[] args) throws NotASCIIException, InvalidLineException, IOException {
        System.out.println("This program checks if characters exist in a given character class read from a file. This program cannot check for whitespace characters.");
        if(args.length != 1) {
            System.out.println("Usage: java Main <file.ccr>");
            System.exit(1);
        }
        File f = new File(args[0]);
        CCFileI fr = new CCFile();
        CharClassLookupI lookup = fr.readCCFile(f);
        Scanner s = new Scanner(System.in);
        System.out.println("Known classes:");
        for(String name: lookup.getNames()) {
            System.out.println(name);
        }
        System.out.print("Which class? ");
        String c = s.nextLine().trim();
        CharClassI cc = lookup.getCC(c);
        if(cc==null) {
            System.out.println("No character class \""+c+"\"");
            return;
        }
        while(c != null) {
            System.out.print("character to check or \"exit\": ");
            c = s.nextLine().trim();
            if(c.equals("exit")) {
                c = null;
                continue;
            }
            if(c.length()!=1) {
                System.out.println("Only one character at a time.");
                continue;
            }
            if(cc.match(c.charAt(0)) ) {
                System.out.println(c+" is in the class");
            } else {
                System.out.println(c+" is not in the class");
            }
        }
        System.out.println("Execution complete");
    }
}
