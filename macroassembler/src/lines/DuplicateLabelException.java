package lines;

import java.io.File;

/** Exception thrown if a duplicate label is detected */
public class DuplicateLabelException extends Exception {
    /** the label that was duplicated */
    private String label;
    /** Construct the exception with the line location and label name
     *@param loc the location the duplicate label was defined
     *@param label the label that was duplicated
     */
    public DuplicateLabelException(LineLocation loc, String label) {
        super(label+" redeclared in "+loc.getFile().getName()+" at line "+loc.getLinenum());
        this.label = label;
    }

    /** Get's the duplicated label
     *@return the label
     */
    public String getLabel() { return label; }
}
