package pony;

public class EarthPony extends Pony {
    // Attributes - What types?
    private String color;
    private String cutiemark;
    private int strength;

    public EarthPony(String n, String c, int s) {
        // Call the constructor for the class we extend
        // In most versions of JAVA this MUST be the first thing done in the constructor
        super(n);
        color = c;
        strength = s;
    }

    public void kick(Pony kickee) {
        kickee.changeHitPoints(-strength);
    }

    // Actions - What should they return and what params should they have
    public void growPlants() {
        // Growing plants is just a message to the console for now
        System.out.println(getName()+" grows plants.");
    }

    @Override
    public void attack(Pony p) {
        // Earth ponies attack by kicking...
        kick(p);
    }
}
