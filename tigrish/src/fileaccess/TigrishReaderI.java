package fileaccess;

import runtime.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/** Interface for objects that read programs targeting the Tigrish runtime */
public interface TigrishReaderI {
    /** Sets the file to be used by this reader
     * <p>Setting the file can only be done once in the lifetime of the reader. Attempts to
     * change the file after it is set should cause an AlreadySetException to be thrown.</p>
     * @throws FileNotFoundException there is a problem opening the file
     * @throws AlreadySetException   the file has already been set
     */
    public void setFile(File f) throws FileNotFoundException, AlreadySetException;

    /** Gets the next node from the source file
     *
     * @return the appropriate Node instance or null if there are no more nodes
     * @throws IOException if there are issues accessing the file
     * @throws SyntaxErrorException if there is a problem parsing the next token from the file
     */
    public Node next() throws IOException, SyntaxErrorException;
}
