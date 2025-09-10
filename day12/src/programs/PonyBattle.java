package programs;

import fileaccess.BadPnyFileExeception;
import fileaccess.PnyReader;
import gui.BattleWindow;
import pony.Pony;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PonyBattle {
    public static void main(String[] args) {
        try {
            PnyReader pr = new PnyReader();
            BattleWindow bw = new BattleWindow();

            File ponyFile = null;
            if(args.length!=0) {
                // use the args for the file instead of a JFileChooser
                ponyFile = new File(args[0]);
            } else {
                // Select the pony file via a JFileChooser ... and start in the data directory because it's easy!
                JFileChooser fc = new JFileChooser(new File("data"));
                // Making an inline anonymous class for the FileFilter instance we're using
                // ...Danielle kind of hates these because she thinks the syntax in really really
                //    gross and hard to read in large codebases... she also thinks they make software
                //    harder to maintain.
                fc.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.getName().endsWith(".pny");
                    }

                    @Override
                    public String getDescription() {
                        return "Yay Ponies! -- .pny";
                    }
                });

                // Present the window to the user
                if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
                    System.out.println("The user did not choose a file containing ponies. Exiting.");
                    System.exit(1);
                }

                // Get the selected file
                ponyFile = fc.getSelectedFile();
            }

            // load the ponies - Use PnyReader instances can do this
            ArrayList<Pony> ponies = pr.read(ponyFile);

            // put ponies into widgets - We have the widgets (PonySelect instances) but not code to set them
            bw.setSelectablePonies(ponies);

            // display the window - BattleWindow, setVisible to display
            bw.setVisible(true);
        } catch (IOException e) {
            // Error message for the user
            System.out.println("There was an IO error... did your hard disk disappear?");
            System.exit(1);
        } catch (BadPnyFileExeception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
