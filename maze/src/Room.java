public class Room {
    protected Room up; // next room in the up direction
    protected Room down; // next room in the down direction
    protected Room left; // next room in the left direction
    protected Room right; // next room in the right direction

    String item; // the item in this room

    public boolean hasAlex() { return "Alex".equals(item); }
    public boolean hasTea() { return "Tea".equals(item); }

    public Room moveUp() throws InvalidMoveException {
        if(up==null) { throw new InvalidMoveException(); }
        return up;
    }
    public Room moveDown() throws InvalidMoveException {
        if(down==null) { throw new InvalidMoveException(); }
        return down;
    }
    public Room moveLeft() throws InvalidMoveException {
        if(left==null) { throw new InvalidMoveException(); }
        return left;
    }
    public Room moveRight() throws InvalidMoveException {
        if(right==null) { throw new InvalidMoveException(); }
        return right;
    }
}