public class Main {
    public static void main(String[] args) {
        Cat tara = new Cat("Tonkinese", "Platinum Mink");
        System.out.println(tara.toString());
        tara.walk(5);
        System.out.println(tara.toString());
        tara.walk(10);
        System.out.println(tara.toString());
    }
}
