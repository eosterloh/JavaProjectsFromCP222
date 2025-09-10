package pony;

public class Pegases extends Pony{
    // Attributes - What types?
    private String color;
    private String cutiemark;
    private int wingspan;
    private boolean flying;

    public Pegases(String n, String c, int wingspan) {
        // If I don't call super(), JAVA will as the first thing in the constructor super()
        // TODO: change this later... for testing all pegasus are pacifists
        super(n, true);
        color = c;
        wingspan = wingspan;
        flying = false;
    }

    public Pegases(String n, String c, int wingspan, boolean p) {
        // If I don't call super(), JAVA will as the first thing in the constructor super()
        // TODO: change this later... for testing all pegasus are pacifists
        super(n, p);
        color = c;
        wingspan = wingspan;
        flying = false;
    }

    public boolean isFlying() {
        return flying;
    }

    public void toggleFlying(){
        flying = !flying;
    }

    public void slash(Pony p) {
        p.changeHitPoints(-wingspan*10);
    }

    // Actions - What should they return and what params should they have

    @Override
    public void attack(Pony p) throws RefusalException {
        if(isPacifist()) {
            throw new RefusalException(getName()+" refuses to attack "+p.getName());
        }
        slash(p);
    }

}
