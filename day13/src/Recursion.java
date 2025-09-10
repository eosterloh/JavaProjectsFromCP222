public class Recursion {
    Recursion l;
    Recursion r;

    public Recursion(int d) {
        if(d>0) {
            l = new Recursion(d-1);
            r = new Recursion(d-1);
        }
    }

    public int eval() {
        if(l==null || r==null) {
            return 1;
        }
        return l.eval() + r.eval();
    }

    public static void main(String[] args) {
        Recursion r = new Recursion(2);
        int x = r.eval();
        // diagram here
        System.out.println(x);
    }
}
