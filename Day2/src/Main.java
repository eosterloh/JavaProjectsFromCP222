public class Main {
    public static void main(String[] args) {

        Node leaf10 = new Node(0,10, null,null);
        Node leaf7 = new Node(1,7,null,null);
        Node leaf4 = new Node(2,4,null,null);
        Node leaf1 = new Node(3,1);
        Node subn = new Node(4, "-",leaf10,leaf7);
        Node muln = new Node(5,"*", leaf4 ,leaf1);
        Node addn = new Node(6,"+",subn,muln);


        System.out.println(addn.toString());
    }
}
