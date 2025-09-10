package lines;

import java.io.File;

/** Represents the location of a line in the computer
 *  <p>Lines exist in files and files exist in the file system. With the
 *  File instance and line number, any line of text can be uniquely
 *  identified.</p>
 */
public class LineLocation {
    /** file where the line occured */
    private File file;
    /** line number within the file the line occured */
    private int linenum;

    /** Makes a line location
     * <p>A line location identifies a line number within a file.</p>
     * @param f the file the line occurs in
     * @param l the line number where the line occurs in the file
     */
    public LineLocation(File f, int l) {
        file    = f;
        linenum = l;
    }

    /** Gets the file this line location occurs in
     *
     * @return the file instance
     */
    public File getFile() {
        return file;
    }

    /** Gets the line number for this line location
     *
     * @return the line number
     */
    public int getLinenum() {
        return linenum;
    }

    /** LineLocations are equal if they are in the same file and reference the same line
     *
     * @param o what to compare to
     * @return true if they are the same location, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        try {
            LineLocation l = (LineLocation) o;
            return l.getFile().equals(getFile()) && l.getLinenum() == getLinenum();
        } catch(ClassCastException e) { }
        return false;
    }

    /** hashcode for a LineLocation
     * <p>According to the API documentation, if you redefine equals you should
     * also redefine hashCode. Failure to do so can cause unexpected behavior in
     * hash based datastructures (e.g. HashMap and HashSet).</p>
     * <p>Requirements for the hashCode value in relation to the equals return
     * value can be found in the JAVA API documentation.</p>
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        if(getFile()==null) {
            return getLinenum();
        }
        return getFile().hashCode() ^ getLinenum();
    }
}
