public class EarthPony extends Pony {

    private String color;
    private String cutiemark;
    private String name;
    private int strength;

    public EarthPony(String name, String color, int strength) {
        this.color = color;
        this.name = name;
        this.strength = strength;
    }

    public void growPlants(){
        System.out.println( name + " is Growing Plants");
    }
    public boolean gallop(int distance){
        boolean tooFar = false;
        if (tooFar) {
            return false;
        }
        return true;
    }

}
