public class UnicornPony extends Pony {
    // Attributes - What types?
    private String color;
    private String cutiemark;
    private String name;
    private String horn;
    private boolean hasGlamour;

    public UnicornPony(String n, String c, String h) {
        name = n;
        color = c;
        horn = h;
    }


    // Actions - What should they return and what params should they have
    public void stab(Pony p) {
        // Do something to determine if the stab is successful
        // TODO: make this random, but always succeeds now
        // update the p's hitpoint
        // TODO: make the damage change
        int damage = -1;
        p.changeHitPoints(damage);
    }

    public void heal(Pony p) {
        p.changeHitPoints(100);
    }

    /**
     * Ask this pony to attack another pony
     *
     * @param p the pony to attack
     */
    @Override
    public void attack(Pony p) {
        // Stab the other pony, violently...
        stab(p);
    }
}
