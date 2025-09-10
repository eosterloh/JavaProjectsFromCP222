public class Pegases extends Pony{
    // Attributes - What types?
    private String color;
    private String cutiemark;
    private String name;
    private int wingspan;
    private boolean flying;

    public Pegases(String n, String c, int wingspan) {
        name = n;
        color = c;
        wingspan = wingspan;
        flying = false;
    }

    public boolean getFlying() {
        return flying;
    }

    public void setFlying(){
        flying = !flying;
    }

    public void slash(Pony p) {
        p.changeHitPoints(wingspan*10);
    }

    // Actions - What should they return and what params should they have
    public void growPlants() {
        // Growing plants is just a message to the console for now
        System.out.println(name+" grows plants.");
    }

    @Override
    public void attack(Pony p) {
        // Earth ponies attack by kicking...
        slash(p);
    }

}
