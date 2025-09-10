package lines;

import java.util.HashMap;

/** Interface for lines from a source file
 * <p>Lines are effectively a linked list. Having the LineI instance for
 * the first line in the file gives access to the entire file.</p> */
public interface LineI {
    /** Produces the corrasponding HMMM assembler line
     *  <p>Causes this line to generate its representation for the output file.
     *  Since this line might require looking up a label target, the HashMap
     *  containing this information is provided as an argument</p>
     * @param labellookup A hashtable mapping label text to a LineLabel instance
     * @return text for this line in the output file
     * @throws NoNextInstructionException if the line uses a label that exists but can't resolve to an instruction address
     * @throws NoSuchLabelException if the line uses a label that does not exist
     */
    public String getOutputText(HashMap<String,LineI> labellookup) throws NoNextInstructionException, NoSuchLabelException;

    /** Produces the address of this line in the output text
     * <p>Comments and Labels don't have their own addresses, only instructions have
     * their own addresses. For a comment or label the address returned should be the
     * address of the next instruction to appear in the source text. If there is no
     * next instruction, then NoNextInstructionException should be thrown with the
     * and reference the last line from the source code.</p>
     * @return an address for this line
     * @throws NoNextInstructionException if there is no address available
     */
    public int getAddress() throws NoNextInstructionException;

    /** Returns the line location
     *
     * @return the location where this source line occurred
     */
    public LineLocation getLineLocation();

    /** Produces the line exactly as it appears in the source text
     *
     * @return the line as it appears in the source text
     */
    public String getSourceText();

    /** The next line in the source file
     * @return the next line from the source file
     */
    public LineI getNextLine();

    /** Sets the next LineI instance for this LineI
     * @param line the line instance that is next
     */
    public void setNextLine(LineI line);
}