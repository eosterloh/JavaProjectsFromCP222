package lines;

import java.util.HashMap;

/**
 * handles the label lines
 */
public class LabelLine extends Line {
    private LineLocation loc;
    private String txt;
    private LineI nextLine;

    /**
     * Labelline Constructor
     * @param loc label line
     * @param txt text of the line
     */
    public LabelLine(LineLocation loc, String txt) {
        this.loc = loc;
        this.txt = txt;
    }

    /** Produces the corrasponding HMMM assembler line
     *  <p>Causes this line to generate its representation for the output file.
     *  Since this line might require looking up a label target, the HashMap
     *  containing this information is provided as an argument</p>
     * @param labellookup A hashtable mapping label text to a LineLabel instance
     * @return text for this line in the output file
     * @throws NoNextInstructionException if the line uses a label that exists but can't resolve to an instruction address
     * @throws NoSuchLabelException if the line uses a label that does not exist
     */
    @Override
    public String getOutputText(HashMap<String,LineI> labellookup) throws NoNextInstructionException, NoSuchLabelException{
        return "# " + getSourceText();
    }

    /** Produces the address of this line in the output text
     * <p>Comments and Labels don't have their own addresses, only instructions have
     * their own addresses. For a comment or label the address returned should be the
     * address of the next instruction to appear in the source text. If there is no
     * next instruction, then NoNextInstructionException should be thrown with the
     * and reference the last line from the source code.</p>
     * @return an address for this line
     * @throws NoNextInstructionException if there is no address available
     */
    @Override
    public int getAddress() throws NoNextInstructionException{
        LineI next = nextLine;
        if (next == null) {
            throw new NoNextInstructionException(this);
        }
        return next.getAddress();
    }

    /** Returns the line location
     *
     * @return the location where this source line occurred
     */
    @Override
    public LineLocation getLineLocation(){
        return loc;
    }

    /** Produces the line exactly as it appears in the source text
     *
     * @return the line as it appears in the source text
     */
    @Override
    public String getSourceText(){
        return txt;
    }

    /** The next line in the source file
     * @return the next line from the source file
     */
    @Override
    public LineI getNextLine(){
        return nextLine;
    }

    /** Sets the next LineI instance for this LineI
     * @param line the line instance that is next
     */
    @Override
    public void setNextLine(LineI line){
        nextLine = line;
    }
}
