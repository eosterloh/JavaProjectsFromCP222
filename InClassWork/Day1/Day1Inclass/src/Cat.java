public class Cat {
    private String breed;
    private String color;
    private Location catLocation;

    public Cat(String b, String c){
        breed = b;
        color = c;
        catLocation = new Location(0);
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public void walk(int distance){
        catLocation.addDisplacement(distance);
    }

    @Override
    public String toString() {
        return breed + " " + color + " " + catLocation;
    }
}