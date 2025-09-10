/** A class representing a point in 2D cartesian space */
public class Point {
    // Attributes
    int x,y; // If we have multiple vars of the same type
             // we can declare them on the same line like this...
             // ...I think it's harder to read but you might see it
             // sometimes in code you read later.

    // Methods
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    void setX(int x) {
        this.x = x;
    }
    void setY(int y) {
        this.y = y;
    }
}
