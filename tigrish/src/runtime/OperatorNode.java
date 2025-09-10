package runtime;


import fileaccess.LineLocation;

import java.util.Scanner;

/**
 * The operator node
 */
public class OperatorNode extends Node {
    private String txt;
    private LineLocation loc;

    /**
     * Operator node constructor
     * @param loc location
     * @param txt text
     */
    public OperatorNode(LineLocation loc, String txt) {
        super(loc,txt);
        this.loc = loc;
        this.txt = txt;
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
        if (txt.equals("+")){
            Node sum1 = env.pop();
            Node sum2 = env.pop();
            if (sum1 instanceof IntNode && sum2 instanceof IntNode) {
                int sum = ((IntNode) sum1).getValue() + ((IntNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if (sum1 instanceof IntNode && sum2 instanceof VarNode) {
                int sum = ((IntNode) sum1).getValue() + ((VarNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if(sum1 instanceof VarNode && sum2 instanceof IntNode) {
                int sum = ((VarNode) sum1).getValue() + ((IntNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if(sum1 instanceof VarNode && sum2 instanceof VarNode) {
                int sum = ((VarNode) sum1).getValue() + ((VarNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else{
                throw new RuntimeFailureException(loc, txt);
            }
        }else if (txt.equals("-")){
            Node sum1 = env.pop();
            Node sum2 = env.pop();
            if (sum1 instanceof IntNode && sum2 instanceof IntNode) {
                int sum = ((IntNode) sum1).getValue() - ((IntNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if (sum1 instanceof IntNode && sum2 instanceof VarNode) {
                int sum = ((IntNode) sum1).getValue() - ((VarNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if(sum1 instanceof VarNode && sum2 instanceof IntNode) {
                int sum = ((VarNode) sum1).getValue() - ((IntNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if (sum1 instanceof VarNode && sum2 instanceof VarNode) {
                int sum = ((VarNode) sum1).getValue() - ((VarNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else{
                throw new RuntimeFailureException(loc, txt);
            }
        }else if(txt.equals("*")){
            Node sum1 = env.pop();
            Node sum2 = env.pop();
            if (sum1 instanceof IntNode && sum2 instanceof IntNode) {
                int sum = ((IntNode) sum1).getValue() * ((IntNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if (sum1 instanceof IntNode && sum2 instanceof VarNode) {
                int sum = ((IntNode) sum1).getValue() * ((VarNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if(sum1 instanceof VarNode && sum2 instanceof IntNode) {
                int sum = ((VarNode) sum1).getValue() * ((IntNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if (sum1 instanceof VarNode && sum2 instanceof VarNode) {
                int sum = ((VarNode) sum1).getValue() * ((VarNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else{
                throw new RuntimeFailureException(loc, txt);
            }
        }else if (txt.equals("/")){
            Node sum1 = env.pop();
            Node sum2 = env.pop();
            //add check to avoid divide by 0 error
            if (sum1 instanceof IntNode && sum2 instanceof IntNode) {
                int sum = ((IntNode) sum1).getValue() / ((IntNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if (sum1 instanceof IntNode && sum2 instanceof VarNode) {
                int sum = ((IntNode) sum1).getValue() / ((VarNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if(sum1 instanceof VarNode && sum2 instanceof IntNode) {
                int sum = ((VarNode) sum1).getValue() / ((IntNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else if (sum1 instanceof VarNode && sum2 instanceof VarNode) {
                int sum = ((VarNode) sum1).getValue() / ((VarNode)sum2).getValue();
                env.push(new IntNode(loc, Integer.toString(sum)));
            }else{
                throw new RuntimeFailureException(loc, txt);
            }
        } else if (txt.equals("=")) {
            VarNode var = (VarNode)env.pop();
            IntNode value = (IntNode)env.pop();
            env.store(var.getText(), new Value(value.getValue()));
        }else if(txt.equals("<")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter an integer: ");
            int value = scanner.nextInt();
            VarNode topnode = (VarNode) env.pop();
            env.store(topnode.getText(), new Value(value));
        }else if(txt.equals(">")) {
            Node topnode = env.pop();
            if (topnode instanceof IntNode){
                System.out.print(((IntNode)topnode).getValue());
            }else if(topnode instanceof VarNode) {
                System.out.print(((VarNode)topnode).getValue());
            }
        }
    }
}
