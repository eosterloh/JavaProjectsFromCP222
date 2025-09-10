package runtime;

import fileaccess.LineLocation;

/**
 * intnode represents ints
 */
public class IntNode extends Node implements ValueI {
    private String txt;
    private LineLocation loc;
    private ValueI val;

    /**
     * IntNode constructor
     * @param location
     * @param srcText
     */
    public IntNode(LineLocation location,String srcText) {
        super(location,srcText);
        this.loc = location;
        this.txt = srcText;
        val = new Value(Integer.parseInt(txt));
    }

    /**
     * Execute the node
     * <p>All Tigrish nodes are executable. The effects to the environment are different for
     * different tokens. The README.txt contains more details.</p>
     *
     * @param env the environment to execute in
     * @throws RuntimeFailureException thrown if the node can't execute for some reason
     */
    @Override
    public void execute(Environment env) throws RuntimeFailureException {
        env.push(this);
    }

    /**
     * Integer get value
     * @return int of value
     */
    public int getValue(){
        return val.getValue();
    }

}
