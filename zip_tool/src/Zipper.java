import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Handles the problem of adding files to a zip archive
 * Usage:
 *      Zipper z = new Zipper(new File(zip_filename));
 *      z.includeFile(fileToInclude, pathToUse);
 *      ...
 *      z.finish();
 */
public class Zipper {
    File archive;
    ZipOutputStream zipout;

    public Zipper(File f) throws FileNotFoundException {
        archive = f;
        zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archive)));
    }

    public void includeFile(File f, String path) throws IOException {
        ZipEntry ze = new ZipEntry(path);
        zipout.putNextEntry(ze);
        byte[] buf = new byte[4096];
        int    br  = 0;
        FileInputStream fr = new FileInputStream(f);
        while(br>=0) {
            br = fr.read(buf);
            if(br>0) {
                zipout.write(buf, 0, br);
            }
        }
        zipout.closeEntry();;
    }

    public void finish() throws IOException {
        zipout.finish();
        zipout.close();
    }
}
