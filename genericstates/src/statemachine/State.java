package statemachine;

import java.util.HashSet;
import java.util.Set;
/** Interface for the representation of a State in a state machine
 * @param <T> The type of the symbol used for transitions
 */
public class State<T> implements StateI<T>{
    private boolean acceptstate;
    private StateI<T> state;
    private String label;
    private Set<TransitionI<T>> transitions;

    /** constructorrrr
     *
     * @param label the Strings label(duh)
     */
    public State(String label) {
        this.acceptstate = false;
        this.label = label;
        this.transitions = new HashSet<TransitionI<T>>();
    }

    /**
     * Label text for this state, may be null
     *
     * @return label state was constructed with
     */
    public String getLabel() {
        return label;
    }

    /**
     * True if this state is an accept state
     *
     * @return true if an accept state, otherwise false
     */
    public boolean isAccept() {
        return acceptstate;
    }

    /**
     * Set whether this state accepts or not
     *
     * @param accept true if the state should be an accept state, otherwise false
     */
    public void setAccept(boolean accept) {
        acceptstate = accept;
    }

    /**
     * All transitions leading out of this state
     * <p>Changes to the returned set must not change the set of transitions
     * in this state.</p>
     *
     * @return state transitions headed out of this node, the set may be empty
     */
    public Set<TransitionI<T>> getTransitions() {
        return transitions;
    }

    /**
     * Adds a new transition from this state
     *
     * @param s   the symbol for the new transition
     * @param nxt the state to transition to on symbol s
     */
    @Override
    public void addTransition(T s, StateI<T> nxt) {
        Transition<T> finalTrans = new Transition<>(s, nxt);
        transitions.add(finalTrans);
    }
}
