package world;

public class Location {
    // Attributes
    private int x,y;
    private boolean flying;

    // Action

    /** Construct a location on the ground or in the air
     *
     * @param x
     * @param y
     * @param flying
     */
    public Location(int x, int y, boolean flying) {
        this.x = x;
        this.y = y;
        this.flying = flying;
    }

    /** Construct a location on the ground
     *
     * @param x
     * @param y
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.flying = false;
    }
    // Getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isFlying() {
        return flying;
    }
    // Setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setFlying(boolean flying) {
        this.flying = flying;
    }

}
