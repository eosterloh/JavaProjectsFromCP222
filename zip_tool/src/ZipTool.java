import java.io.File;
import java.util.List;

public class ZipTool {
    public static void main(String[] args) throws Exception {
        // Figure out the files to work with and assignment directory name
        Gather g = new Gather();
        g.setProjectDir();
        g.gatherFiles();
        List<File> fileList = g.getToInclude();

        g.setAssignmentDir();

        // Verify that we have the files we should
        System.out.println("Are these the correct files:");
        for(File f: fileList) {
            System.out.println(g.archiveName(f));
        }
        if(!CLIstuff.getYesNo()) {
            CLIstuff.modifyList(g);
        }
        System.out.println("Creating a archive with the following files:");
        for(File f: fileList) {
            System.out.println(g.archiveName(f));
        }
        g.setOutputFile();

        Zipper zipper = new Zipper(g.getOutputFile());
        // Everything should be ready to go now...
        for(File f: fileList) {
            zipper.includeFile(f, g.archiveName(f));
        }
        zipper.finish();

        System.out.println("Zip archive created");
        System.out.println(g.getOutputFile());
    }
}
