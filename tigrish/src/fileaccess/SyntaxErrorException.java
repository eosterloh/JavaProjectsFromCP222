package fileaccess;

/** Signals that there is something unparsable encountered in the program text
 *
 */
public class SyntaxErrorException extends Exception {
    /** Create an instance
     *
     * @param loc location in the source file
     * @param msg information about the issue
     */
    public SyntaxErrorException(LineLocation loc, String msg) {
        super("Error in "+loc.getFile().getName()+" at line "+loc.getLinenum()+": "+msg);
    }
}
