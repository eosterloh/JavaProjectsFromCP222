package statemachine;

/** Represents a transition in a state machine
 * @param <T> The type of the symbol used for transitions
 */
public class Transition<T> implements TransitionI {
    private StateI<T> next;
    private T symbol;

    /** constructorrrrr
     *
     * @param sym the transitions symbol
     * @param n next state
     */
    public Transition(T sym , StateI<T> n){
        next = n;
        symbol = sym;
    }
    /**
     * Gets the destination of this transition
     *
     * @return state reached by taking this edge
     */
    public StateI<T> nextState() {
        return next;
    }

    /**
     * Gets the symbol that this transition can occur on
     *
     * @return symbol for this edge
     */
    public T getSymbol() {
        return symbol;
    }
}
