public class UnicornPony extends Pony {
    private String color;
    private String name;
    private String cutiemark;
    private String horn;
    private boolean hasGlamour;
    private int health;

    public UnicornPony(String n, String c, String h){
        name = n;
        color = c;
        horn = h;
    }

    public void stab(UnicornPony p){

    }

    public boolean gallop(int distance){
        boolean tooFar = false;
        if (tooFar) {
            return false;
        }
        return true;
    }
}
