package lines;

/** Exception thrown if seeking a next instruction fails */
public class NoNextInstructionException extends Exception {
    /** the line instance in the exception */
    private LineI line;

    /** Create an instance for a specific line
     * The line that encountered the problem
     * @param l the line instance that initially threw the exception
     */
    public NoNextInstructionException(LineI l) {
        super("No instraction after line "+l.getLineLocation().getLinenum()+" in "+l.getLineLocation().getFile().getName());
        line = l;
    }

    /** Get's the line instance that threw this exception
     *
     * @return the line where the exception was initially thrown
     */
    public LineI getLine() { return line; }
}
