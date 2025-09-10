public class Ponyville {
    public static void main(String[] args) {
        Pony[] ponies = new Pony[6];
        ponies[0] = new EarthPony("Max", "Red", 1000);
        ponies[1] = new EarthPony("Eric", "Green", 500);
        ponies[2] = new UnicornPony("Soulbreaker", "red", "long");
        ponies[3] = new UnicornPony("Tina", "Blue", "red");

        ponies[0].growPlants();
        ponies[1].growPlants();

        for (int i = 0; i < ponies.length; i++) {
            ponies[i].gallop();
        }
    }
}