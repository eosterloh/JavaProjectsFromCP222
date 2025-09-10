import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * Gathers the information regarding which files to work with
 */
public class Gather {
    String assignment_dir; // the prefix for all files
    File project_dir;    // the directory to find files in
    File outfile;

    List<File> toInclude = new ArrayList<File>();

    public void setAssignmentDir() {
        System.out.print("What should the assignment directory name be: ");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        assignment_dir = line;
    }
    public String getAssignmentDir() {
        return assignment_dir;
    }

    public void setProjectDir() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);
        project_dir = fc.getSelectedFile();
        if(project_dir == null || !project_dir.isDirectory()) {
            System.out.println("No directory selected for the root of the project...");
            System.exit(1);
        }
    }
    public File getProjectDir() {
        return project_dir;
    }

    public void setOutputFile() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        outfile = null;
        while(outfile==null) {
            fc.showSaveDialog(null);
            outfile = fc.getSelectedFile();
            if (outfile == null || outfile.isDirectory()) {
                System.out.println("No file name selected for the zip file...");
                System.exit(1);
            }
            if(!outfile.getName().endsWith(".zip")) {
                JOptionPane.showMessageDialog(null, "Filename must end in '.zip'","Invalid Filename",ERROR_MESSAGE);
                outfile = null;
            }
        }
    }
    public File getOutputFile() {
        return outfile;
    }

    public void gatherFiles() {
        File[] files = getProjectDir().listFiles();
        for(File f:files) {
            // skip the build directory and metadata
            if(f.getName().equals("out")) { continue; }
            if(f.getName().startsWith(".")) { continue; }
            if(f.isHidden() || f.getName().endsWith(".iml")) { continue; }
            if(f.isDirectory()) { this.gatherFilesRec(f); }
            if(f.isFile()) { toInclude.add(f); }
        }
    }
    public List<File> getToInclude() { return toInclude; }
    public void gatherFilesRec(File parent) {
        File[] files = parent.listFiles();
        for(File f:files) {
            if(f.isHidden()) { continue; }
            if(f.getName().startsWith(".")) { continue; }
            if(f.isDirectory()) { gatherFilesRec(f); }
            if(f.isFile()) { toInclude.add(f); }
        }
    }

    public String archiveName(File f) {
        int parts = getProjectDir().toPath().getNameCount();
        Path p = f.toPath();
        p = p.subpath(parts, p.getNameCount());
        return assignment_dir+"/"+p.toString().replaceAll("\\\\","/"); //Windows seperator is weird
    }
}
