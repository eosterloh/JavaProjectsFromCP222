package lines;

/** Indicates that a label could not be found */
public class NoSuchLabelException extends Exception {
    /** The line where the unknown label was used */
    private LineI line;

    /** Create a new instance
     *
     * @param l the line containing the unknown label
     */
    public NoSuchLabelException(LineI l) {
        super("Unknown label used on line "+l.getLineLocation().getLinenum()+" in "+l.getLineLocation().getFile().getName());
        line = l;
    }

    /** Gets the line with teh unknown label
     *
     * @return the offending line
     */
    public LineI getLine() { return line; }
}
