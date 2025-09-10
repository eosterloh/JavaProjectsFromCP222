package fileaccess;

import statemachine.StateI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/** Interface for classes that instantiate state machines from files or byte streams
 * @param <T> The type of the symbol used for transitions
 */
public interface StateMachineGenI<T> {

    /** Instantiates a state machine from the contents of a file
     *
     * @param f the file containing the machine description
     * @return The start state of the machine
     * @throws InvalidStateMachineException when there are errors in the description
     * @throws FileNotFoundException when the file cannot be found/read
     * @throws IOException when the underlying libraries throw IOException
     */
    public StateI<T> read(File f) throws InvalidStateMachineException, FileNotFoundException, IOException;

}
