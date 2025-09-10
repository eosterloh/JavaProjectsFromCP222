/** A program to play with our pony implementation in */
public class Ponyville {
    public static void main(String[] args) {
        // Make some ponies
        // NOTE: ...there's probably a runtime problem hiding here...
        Pony[] ponies = new Pony[6];
        ponies[0] = new EarthPony("Max","pink", 100);
        ponies[1] = new EarthPony("Eric","orange", -1);
        ponies[2] = new UnicornPony("SoulBreaker", "red","long");
        ponies[3] = new UnicornPony("Tina","blue", "red");

        // Want code such that the pony at i "attacks" the pony at (i+1)%4
        // ...would prefer to do this with a loop rather than 4 lines of code...
        for(int i=0; i<4; i++) {
            ponies[i].attack(ponies[(i+1)%4]);
        }

        // Test kicking
        System.out.println("kick test...");
        try {
            // I will not remove or change the line no mater how nicely or
            // cruelly you ask... How do we make the program not crash
            // when I'm trouble like this?
            EarthPony kicker = (EarthPony) ponies[3];
            Pony      kickee = ponies[2];
            System.out.println("Kickee hp before: "+kickee.getHitpoints());
            kicker.kick(kickee);
            System.out.println("Kickee hp after: "+kickee.getHitpoints());

        } catch (ClassCastException e) {
            // What to do instead...
            EarthPony kicker = (EarthPony) ponies[0];
            Pony      kickee = ponies[2];
            System.out.println("Kickee hp before: "+kickee.getHitpoints());
            kicker.kick(kickee);
            System.out.println("Kickee hp after: "+kickee.getHitpoints());

        }


        // Make them grow plants -- still need to cast grow plants (not a Pony method)
        ((EarthPony)ponies[0]).growPlants();
        ((EarthPony)ponies[1]).growPlants();

        // Make the ponies gallop
        for(int i=0; i<4; i++) {
            ponies[i].gallop(5);
        }


        // Display the hitpoints for the ponies...
        UnicornPony attacker = (UnicornPony)ponies[3];
        Pony attackee = ponies[0];

        System.out.println("Attacker HP: "+attacker.getHitpoints());
        System.out.println("Attackee HP: "+attackee.getHitpoints());

        // violence starts here... But it's ok, because it's actually healing...
        attacker.stab(attackee);
        attacker.heal(attackee);
        // Display the hitpoints for the ponies...
        System.out.println("Attacker HP: "+attacker.getHitpoints());
        System.out.println("Attackee HP: "+attackee.getHitpoints());

        // Anyone could make them really negative if public... change to
        // private and make a getter
        //attacker.hitpoints = -100000;
        // Anyone could write this...
        // Attacker(S) change your hitpoint by (V) -10000 (O)
        attacker.changeHitPoints(-10000);
        System.out.println(attacker.getHitpoints());

        // Test kicking

    }
}
