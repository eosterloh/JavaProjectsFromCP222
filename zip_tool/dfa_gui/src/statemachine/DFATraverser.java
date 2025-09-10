package statemachine;

import java.util.Iterator;

public class DFATraverser<T> implements TraverserI<T> {
    StateI<T> lastState;
    int       numMatched;

    /** Is string accepted by this DFA?
     *
     * @param machine the start state of the DFA
     * @param string the string to match
     * @return true if it is accepted, otherwise false
     */
    public boolean isAccepted(StateI<T> machine, Iterable<T> string) {
        if(!traverse(machine, string)) {
            return false;
        }
        return lastState.isAccept();
    }

    /**
     * Start a traversal of the state machine
     *
     * @param state  the state to traverse from
     * @param string the sequence of symbols to traverse with
     * @return true if all symbols in string were consumed
     */
    @Override
    public boolean traverse(StateI<T> state, Iterable<T> string) {
        Iterator<T> itr = string.iterator();
        numMatched = 0;
        return traverse(state, itr);
    }

    private boolean traverse(StateI<T> state, Iterator<T> itr) {
        lastState = state;
        if(!itr.hasNext()) {
            return true;
        }
        T sym = itr.next();
        for(TransitionI<T> t: state.getTransitions()) {
            if(t.getSymbol().equals(sym)) {
                numMatched++;
                return traverse(t.nextState(), itr);
            }
        }
        return false;
    }

    /**
     * The last state reached by the traverser
     *
     * @return last state reached or null when traverse has not been called yet
     */
    @Override
    public StateI<T> getFinalState() {
        return lastState;
    }

    /**
     * The number of symbols matched by the traverser
     * @return the number of transitions taken
     */
    public int getNumMatched() {
        return numMatched;
    }
}
