// Don't want to use an interface because there are common attributes
// and actions
//public interface Pony {

// Don't want to use a regular class because we don't want some joker
// working on the codebase later to instantiate a Pony directly... Require
// ALL ponies to always be constructed as a specific type.
// public class Pony {

// An abstract class can define instance variables and methods
// but does not allow direct instantiation.
public abstract class Pony {
    // hitpoints should never be negative!
    private int hitpoints = 100;

    /** Ask this pony to attack another pony
     *
     * @param p the pony to attack
     */
    // An abstract method can only exist in an abstract class
    // ALL classes that extend this class MUST implement this method
    public abstract void attack(Pony p);

    /**
     * Ask the pony to gallop
     * <p>Change the location of the pony based on distance, unless the pony
     * is asked to move too far.</p>
     *
     * @param distance how far
     * @return if the gallop was successful
     */
    public boolean gallop(int distance) {
        boolean tooFar = false;
        // TODO: determine some rules for what is too far
        if (tooFar) {
            return false;
        }
        // Change the location
        // TODO: figure out how to update locations
        return true;
    }

    /** Changes the hp by some amount
     * @param amount the amount to change the hitpoints by
     */
    public void changeHitPoints(int amount) {
        // this is the ponie than is being "hurt"
        // Want to make sure no one can pass in an amount that makes this negative
        if(-amount>hitpoints) {
            hitpoints = 0;
            return;
        }
        hitpoints += amount;
    }

    public int getHitpoints() {
        return hitpoints;
    }
}
