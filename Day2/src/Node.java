public class Node {
    int id;

    Object value;

    //children
    Node right;
    Node left;
    public Node(int id, Object o, Node l,Node r) {
        this.id = id;
        value = o;

        right = r;
        left = l;
    }
    public Node(int id, Object o) {
        this.id = id;
        value = o;
    }

    public String toString(){
        if(left == null && right == null){
            return value.toString();
        }
        return "(" + left.toString() + value.toString()+right.toString() +")";
    }
}


