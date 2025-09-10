/** Exception to inform the caller that the filename hasn't been set or
    has not been set to a valid file
*/
public class UnsetFilenameException extends Exception {
    /** Create a new instance of this exception type
    */
    public UnsetFilenameException() {
        super("setFilename() must be called with a non-null String");
    }
}
