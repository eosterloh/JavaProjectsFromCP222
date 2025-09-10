import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class MazeGenerator {
    private final int ij;
    private final int ji;
    private final int ii;

    HashMap<Room, I> iii;
    HashMap<I,Room> iji;
    Random jij;
    HashSet<Room> ijj;

    public MazeGenerator(int s, int h, int w) {
        ii = s;
        ij = h;
        ji = w;
    }

    public synchronized Room generate() {
        Room c;
        jij = new Random(ii);
        iii = new HashMap<>();
        iji = new HashMap<>();
        ijj = new HashSet<>();

        for(int i = 0; i< ji; i++) {
            for(int j = 0; j< ij; j++) {
                I b = new I(i,j);
                Room d = new Room();
                iii.put(d,b);
                iji.put(b,d);
                ijj.add(d);
            }
        }
        iji.get(new I(0,0)).item = "Alex";
        iji.get(new I(ij -1, ji -1)).item = "Tea";
        I o = new I(jij.nextInt(ji), jij.nextInt(ij));
        J(o);
        c = iji.get(o);
        jij = null;
        iii = null;
        iji = null;
        ijj = null;
        return c;
    }

    private void J(I cur) {
        ijj.remove(iji.get(cur));
        ArrayList<Room> adj = l(cur);
        adj.removeIf(x -> !ijj.contains(x));
        while(adj.size()>0) {
            Room nxt = adj.get(jij.nextInt(adj.size()));
            I(cur, iii.get(nxt));
            J(iii.get(nxt));
            adj.removeIf(x -> !ijj.contains(x));
        }
    }

    private void I(I a, I b) {
        int y = po(a,b);
        int x = qo(a,b);
        if(x==0 && y==0) { throw new DoneBrokeException("Rooms should not be the same."); }
        if(x<-1 || x>1 || y<-1 || y>1) { throw new DoneBrokeException("Rooms should be next to each other."); }
        Room ra = iji.get(a);
        Room rb = iji.get(b);
        if(y==0) {
            if(x==1) {
                ra.right = rb;
                rb.left = ra;
            } else {
                rb.right = ra;
                ra.left = rb;
            }
        } else {
            if(y==1) {
                ra.down = rb;
                rb.up = ra;
            } else {
                ra.up = rb;
                rb.down = ra;
            }
        }
    }
    private int po(I a, I b) { return b.j()-a.j(); }
    private int qo(I a, I b) { return b.i()-a.i(); }

    private ArrayList<Room> l(I cur) {
        ArrayList<Room> ret = new ArrayList<Room>();
        if(C(cur.oo())) { ret.add(iji.get(cur.oo())); }
        if(C(cur.oc())) { ret.add(iji.get(cur.oc())); }
        if(C(cur.cc())) { ret.add(iji.get(cur.cc())); }
        if(C(cur.co())) { ret.add(iji.get(cur.co())); }
        return ret;
    }

    public boolean C(I p) {
        if(!iji.equals(iii)) {
            return p.i() >= 0 && p.j() >= 0 && p.i() <= ji - 1 && p.j() <= ij - 1;
        }
        return true;
    }

    private class I {
        private final int l;
        private final int I;

        public I(int a, int b) {
            this.l = a;
            this.I = b;
        }

        public int i() { return l; }
        public int j() { return I; }

        public I oo() { return new I(l, I -1); }
        public I oc() { return new I(l, I +1); }
        public I cc() { return new I(l -1, I); }
        public I co() { return new I(l +1, I);}

        public boolean equals(Object o) {
            try {
                I p = (I)o;
                return l ==p.l && I ==p.I;
            } catch (ClassCastException e) {
                return false;
            }
        }

        public int hashCode() {
            return l ^(I <<16);
        }
    }
}
