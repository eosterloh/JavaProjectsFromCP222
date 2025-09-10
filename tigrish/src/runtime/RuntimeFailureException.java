package runtime;

import fileaccess.LineLocation;

/** Signals that a problem occurred at runtime
 *
 */
public class RuntimeFailureException extends Exception {
    /** Create an instance
     *
     * @param loc where in the file the failing instruction appears
     * @param msg some message to help with debugging
     */
    public RuntimeFailureException(LineLocation loc, String msg) {
        super("Runtime failure in "+loc.getFile().getName()+"@"+loc.getLinenum()+" : "+msg);
    }
}
