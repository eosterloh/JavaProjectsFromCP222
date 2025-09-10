package lines;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;

/** Class that take line text and produces the right kind of lines.LineI instance.
 *  <p>A lines.LineFactory creates and holds the lookup table to convert label
 *  targets into their corresponding lines.LineI instance.</p> */
public class LineFactory {
    /** Regex for label lines */
    private Pattern label_ptrn = Pattern.compile("^\\s*:(\\w+):");
    /** Regex for comment lines */
    private Pattern comment_ptrn = Pattern.compile("^\\s*(#.*)?$");

    private HashMap<String,LineI> lookup; // lookup table to help with mapping label text to label instances
    private int srcLineNum=0; // counter for the source lines
    private int instrNum=0;   // counter for the instruction addresses

    /** Creates a lines.LineFactory */
    public LineFactory() {
        lookup = new HashMap<String,LineI>();
    }

    /** Creates a line instance using this lines.LineFactory
     *  <p>LabelLines are constructed with the source line number and
     *  the source line text. After constructing a LabelLine, the
     *  lines.LineFactory updates the lookup map.</p>
     *  <p>CommentLines are constructed with the source line number and
     *  the source line text.</p>
     *  <p>Anything that is not a Label or Comment Line is assumed to
     *  be an InstrLine. InstrLines are constructed with the source line
     *  number, instruction number, and source line text.</p>
     * @param loc the location this line occurred on
     * @param txt raw line of text from the source file
     * @return line instance
     * @throws DuplicateLabelException if a label is redefined
     */
    public LineI createLine(LineLocation loc, String txt) throws DuplicateLabelException {
        // Read a line, so advance the source line counter
        srcLineNum++;
        // check if this is a label line
        Matcher m = label_ptrn.matcher(txt);
        if(m.find()) {
            String label = m.group(1);
            LineI ll = new LabelLine(loc, txt);
            if(lookup.containsKey(label)) {
                throw new DuplicateLabelException(loc, label);
            }
            lookup.put(label,ll);
            return ll;
        }
        // check if this is a comment line
        m = comment_ptrn.matcher(txt);
        if(m.find()) {
            LineI cl = new CommentLine(loc, txt);
            return cl;
        }
        // Must be an instruction line instrNum is synonymous with address for
        //      instruction lines
        // ....would the behavior be the same if ++instrNum was used instead?
        return new InstrLine(loc, instrNum++, txt);
    }

    /** Retrieves the label to lines.LineI mapping for this lines.LineFactory
     *
     * @return the map used by this instance
     */
    public HashMap<String,LineI> getLookup() {
        return lookup;
    }
}