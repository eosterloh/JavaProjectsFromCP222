package runtime;

import fileaccess.LineLocation;

/** Representation of a Tigrish token/node for the runtime */
public abstract class Node {
    /** The raw token text */
    private String srcText;
    /** The file and line the token is from */
    private LineLocation location;

    /** Construct the abstract node
     *
     * @param loc where the token occured
     * @param srcText the raw text for the token
     */
    public Node(LineLocation loc, String srcText) {
        location = loc;
        this.srcText = srcText;
    }

    /** get the raw text for the token
     *
     * @return raw token text
     */
    public String getText() {
        return srcText;
    }

    /** Get the source file location of the token
     *
     * @return the location the token was read from
     */
    public LineLocation getLocation() { return location; }

    /** Execute the node
     * <p>All Tigrish nodes are executable. The effects to the environment are different for
     * different tokens. The README.txt contains more details.</p>
     * @param env the environment to execute in
     * @throws RuntimeFailureException thrown if the node can't execute for some reason
     */
    public abstract void execute(Environment env) throws RuntimeFailureException;
}
