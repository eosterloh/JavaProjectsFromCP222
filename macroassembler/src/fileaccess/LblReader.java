package fileaccess;

import lines.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/***
 * Reads the labels
 */
public class LblReader implements LblReaderI{
    private ArrayList<LineI> alreadyread = new ArrayList<>();
    private ArrayList<LineI> read;
    private ArrayList<String> lines = new ArrayList<>();
    private LineFactory lf = new LineFactory();


    /**
     * Adds the lines in a file to the lines read
     * <p>If read has been called previously, this method <b>will not</b> reset the
     * lines from the previous call. Instead, the lines from this file will
     * be appended after the lines from the first file.</p>
     *
     * @param f the file to read
     * @throws BadFileTypeException    if the file does not end in .lbl
     * @throws DuplicateLabelException if a label is declared more than once
     * @throws FileNotFoundException   if the file can't be found
     * @throws IOException             if there is an underlying IO issue
     */
    public void read(File f) throws BadFileTypeException, DuplicateLabelException, FileNotFoundException, IOException {
        //need some way to get lines that have been read already
        //
        if (!f.getName().endsWith(".lbl")) {
            throw new BadFileTypeException(f, ".hmmm");
        }

        BufferedReader r = new BufferedReader(new FileReader(f));

        LineI prevline = null;
        int linenum = 0;
        String lineadd;
        while((lineadd = r.readLine()) != null){
            lines.add(lineadd);
        }

        for (String line: lines){
            LineI curline = lf.createLine(new LineLocation(f, linenum), line);
            if(curline instanceof InstrLine){
                linenum++;
            }
            if(prevline != null){
                prevline.setNextLine(curline);
            }
            prevline = curline;
            alreadyread.add(curline);
        }
    }

    /**
     * Returns all lines read since the LblReader was constructed
     * <p>Lines are in the same order they were read.</p>
     *
     * @return the first line
     * @throws NoLinesException if no calls to read have occured
     */
    public LineI getFirstLine() throws NoLinesException {
        if (lines.isEmpty()){
            throw new NoLinesException();
        }
        return alreadyread.get(0);
    }

    /**
     * Returns the label line lookup
     * <p>The map will be empty if no labels have been read.</p>
     *
     * @return the label line lookup
     */
    public HashMap<String, LineI> getLabelLookup() {
        return lf.getLookup();
    }
}
