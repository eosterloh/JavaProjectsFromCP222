/** A class representing a point in 2D cartesian space */
public class Point {
    // Attributes
    private int x,y; // If we have multiple vars of the same type
             // we can declare them on the same line like this...
             // ...I think it's harder to read but you might see it
             // sometimes in code you read later.

    // Methods
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    /** Equal if the same x,y pair
     *
     * @param o other point
     * @return judgement
     */
    // Note: JAVA looks up by name and parameter type... since this overloads equals in Object
    //       the parameter type needs to be Object
    public boolean equals(Object o) {
        Point p = (Point)o;
        return this.x == p.x && this.y == p.y;
    }
}
