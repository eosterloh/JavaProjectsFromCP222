package runtime;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Stack;

/** The Tigrish execution environment */
public class Environment {
    /** The stack memory */
    private Stack<Node> registers;
    /** The variable memory */
    private HashMap<String, ValueI> symboltable;

    /** Create a new empty environment */
    public Environment() {
        registers = new Stack<>();
        symboltable = new HashMap<>();
    }

    /** Push a node onto the stack
     *
     * @param n the node instance to push
     */
    public void push(Node n) {
        registers.push(n);
    }

    /** Pop a node instance from the stack
     *
     * @return the node popped
     */
    public Node pop() throws EmptyStackException {
        return registers.pop();
    }

    /** Store a variable value in the symbol table
     *
     * @param symbol the variable name
     * @param node   the node to associate with that name
     */
    public void store(String symbol, ValueI node) {
        symboltable.put(symbol, node);
    }

    /** Fetch a variable value from the symbol table
     *
     * @param symbol the variable name
     * @return the value for that symbol
     * @throws NoValueException if the variable doesn't have a value
     */
    public ValueI fetch(String symbol) throws NoValueException {
        ValueI ret = symboltable.get(symbol);
        if(ret==null) {
            throw new NoValueException();
        }
        return ret;
    }
}
