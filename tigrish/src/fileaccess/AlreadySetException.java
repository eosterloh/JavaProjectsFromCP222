package fileaccess;

import java.io.File;

/** Used to single that the file has already been set
 *
 */
public class AlreadySetException extends Exception {
    /** Make an instance
     *
     * @param f the file currently being used
     */
    public AlreadySetException(File f) {
        super("The file has already been set to "+f.getName());
    }
}
