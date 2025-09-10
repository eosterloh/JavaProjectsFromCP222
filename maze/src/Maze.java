import javax.swing.*;
import java.io.*;

/** Generates and runs a maze */
public class Maze {
    public static void main(String[] args) throws IOException {
        final String username;
        final String movesfile;
        // Check that the program is started correctly
        if(args.length==0) {
            // Trying to run in GUI mode since CLI args are missing...
            username = gui_username();
            movesfile = gui_filename();
        } else if(args.length != 2) {
            // Arg count is wrong, provide a usage message
            System.out.println("Usage: java Maze <username> <movefile.txt>");
            System.exit(1);
            return; // <-- this statement is never reached but is required to satisfy javac's static analysis
        } else {
            // Trying to run in CLI mode...
            username = args[0];
            movesfile = args[1];
        }

        // Create the maze and get the room the player starts in
        boolean hasTea = false;
        MazeGenerator mg = new MazeGenerator(username.hashCode(),8,8);
        Room cur = mg.generate(); // <-- the player starts in this room

        // Open the movefile
        BufferedReader fr = new BufferedReader(new FileReader(movesfile));
        String line = fr.readLine().toLowerCase();
        // Check that the maze file comment matches the username provided
        if(!(line.charAt(0)=='#' && line.substring(1).equals(username))) {
            System.out.println("Username and movefile do not match");
            System.exit(1);
        } else {
            System.out.println("Running maze for "+username);
        }

        // Take the steps in the file one at a time in order
        int stepnum = 0;
        try {
            while (line != null) {
                stepnum++;
                line = line.toLowerCase();
                if(line.isEmpty()) { line=" "; }
                char dir = line.charAt(0);
                if (dir == 'u') {
                    // Move player up
                    cur = cur.moveUp();
                } else if (dir == 'd') {
                    // Move player down
                    cur = cur.moveDown();
                } else if (dir == 'l') {
                    // Move player left
                    cur = cur.moveLeft();
                } else if (dir == 'r') {
                    // Move player right
                    cur = cur.moveRight();
                } else {
                    // Unknown move, do nothing
                }
                // pickup the tea if there is tea here
                if (cur.hasTea()) {
                    hasTea = true;
                }
                // Get ready for the next iteration of the loop
                line = fr.readLine();
            }
        } catch (InvalidMoveException e) {
            System.out.println("You can't move that way because of the wall!");
            System.out.println("Wall smash happened on line number "+stepnum+" of the file.");
            System.exit(1);
        }
        if(hasTea && cur.hasAlex()) {
            // Move file ends on Alex after getting the tea
            System.out.println("Yay! You have a tea party with Alex!");
            System.exit(0);
        } else if(hasTea) {
            System.out.println("Oh no! You didn't find Alex with the tea... and now the tea is cold!");
            System.exit(1);
        } else {
            System.out.println("Oh no! No tea and no Alex!");
            System.exit(1);
        }
    }

    private static String gui_username() {
        String uname = JOptionPane.showInputDialog("Enter username for the maze");
        return uname;
    }

    private static String gui_filename() {
        // Prompt user for data directory
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
        File datafile = fc.getSelectedFile();
        if(datafile == null) {
            System.out.println("No file selected...");
            System.exit(1);
        }
        return datafile.getPath();
    }
}
