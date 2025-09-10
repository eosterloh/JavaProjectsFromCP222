package runtime;

import fileaccess.LineLocation;

/**
 * VarNode class represents var nodes
 */
public class VarNode extends Node implements ValueI{
    private String name;
    private Environment env;
    private LineLocation loc;

    /**
     * COnstructorrrr for varnode
     * @param loc loction of var
     * @param txt src txt
     */
    public VarNode(LineLocation loc, String txt) {
        super(loc,txt);
        name = txt;
        this.loc = loc;

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
     * the value of var Node
     * @return int this returns the int
     */
    public int getValue() {
        try {
            return env.fetch(name).getValue();
        }
        catch (NoValueException ignored) {
            return 0;
        }
    }
}
