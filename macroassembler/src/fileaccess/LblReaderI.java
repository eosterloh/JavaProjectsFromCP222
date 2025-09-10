package fileaccess;

import lines.DuplicateLabelException;
import lines.LineI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/** Interface for objects that read .lbl files */
public interface LblReaderI {
    /** Adds the lines in a file to the lines read
     *  <p>If read has been called previously, this method <b>will not</b> reset the
     *  lines from the previous call. Instead, the lines from this file will
     *  be appended after the lines from the first file.</p>
     * @param f the file to read
     *
     * @throws BadFileTypeException if the file does not end in .lbl
     * @throws DuplicateLabelException if a label is declared more than once
     * @throws FileNotFoundException if the file can't be found
     * @throws IOException if there is an underlying IO issue
     */
    public void read(File f) throws BadFileTypeException, DuplicateLabelException, FileNotFoundException, IOException;

    /** Returns all lines read since the LblReader was constructed
     *  <p>Lines are in the same order they were read.</p>
     * @return the first line
     * @throws NoLinesException if no calls to read have occured
     */
    public LineI getFirstLine() throws NoLinesException;

    /** Returns the label line lookup
     *  <p>The map will be empty if no labels have been read.</p>
     * @return the label line lookup
     */
    // Note: you should be able to get this HashMap from the LineFactory
    public HashMap<String, LineI> getLabelLookup();
}
