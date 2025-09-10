package statemachine;

import java.util.Set;

/** Interface for the representation of a State in a state machine
 * @param <T> The type of the symbol used for transitions
 */
public interface StateI<T> {
    /** Label text for this state, may be null
    *@return label state was constructed with
    */
    public String getLabel();

    /** True if this state is an accept state
    *@return true if an accept state, otherwise false
    */
    public boolean isAccept();

    /** Set whether this state accepts or not
     *
     * @param accept true if the state should be an accept state, otherwise false
     */
    public void setAccept(boolean accept);

    /** All transitions leading out of this state
     * <p>Changes to the returned set must not change the set of transitions
     * in this state.</p>
     * @return state transitions headed out of this node, the set may be empty
     */
    public Set<TransitionI<T>> getTransitions();


    /** Adds a new transition from this state
     *
     * @param s the symbol for the new transition
     * @param nxt the state to transition to on symbol s
     */
    public void addTransition(T s, StateI<T> nxt);

}
