import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Helpers for getting input
 */
public class CLIstuff {
    public static boolean getYesNo() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("(Yes) or No: ");
            String input = sc.nextLine();
            input = input.toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            }
            if (input.equals("no") || input.equals("n")) {
                return false;
            }
            System.out.print("Input was not understood... Try again.");
        }
    }

    public static void modifyList(Gather g) {
        System.out.println("Hmmm... The list should contain all non-hidden and not IDE metadata files...");
        System.out.println("If exepected files are missing, there is a bug in the program.");
        System.out.println("If there are extra files, we can remove them.");

        Scanner sc = new Scanner(System.in);
        List<File> fileList = g.getToInclude();
        while(true) {
            int i = 0;
            for (File f : fileList) {
                System.out.println(String.format("%02d", i) + ": " + g.archiveName(f));
                i++;
            }
            System.out.print("Enter the number of a file to omit, (d) to be done: ");
            String line = sc.nextLine();
            if(line.equals("d")) {
                break;
            }
            int n = Integer.parseInt(line);
            fileList.remove(n);
        }
        System.out.println("List finalized.");
    }
}
