package runtime;

import fileaccess.LineLocation;

import javax.sound.sampled.Line;

public class CommentNode extends Node {
    private LineLocation loc;
    private String srcText;
    public CommentNode(LineLocation loc, String text) {
        super(loc,text);
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

    }
}
