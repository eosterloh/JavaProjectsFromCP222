package statemachine;


/** Things that traverse state machines to perform computations
 * @param T the type for transition symbols
 */
public interface TraverserI<T> {

    /** Start a traversal of the state machine
     *
     * @param state the state to traverse from
     * @param string the sequence of symbols to traverse with
     * @return true if all symbols in string were consumed
     */
    public boolean traverse(StateI<T> state, Iterable<T> string);

    /** The last state reached by the traverser
     *
     * @return last state reached or null when traverse has not been called yet
     */
    public StateI<T> getFinalState();
}
