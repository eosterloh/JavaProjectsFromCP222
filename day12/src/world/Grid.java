package world;

import pony.Pony;

/** a 2D x2 grid to hold the ponies */
public class Grid extends Object {
    // Attributes
    // Dimensions of the grid
    private int sizeX;
    private int sizeY;
    // Forces only 1 pony per grid location... Do we want that? kind of...
    // Need two arrays
    private Pony[][] land_data;
    private Pony[][] sky_data;

/*
    // "Actions"
    public world.Location getLocation(pony.Pony p) {
        // How do I find a pony on the grid?
        // Scan the land...
        for(int x=0; x<sizeX; x++) {
            for(int y=0; y<sizeY; y++) {
                if(land_data[x][y].equals(p)) {
                    return new world.Location(x,y);
                }
            }
        }
        // Scan the air..
        for(int x=0; x<sizeX; x++) {
            for(int y=0; y<sizeY; y++) {
                if(sky_data[x][y].equals(p)) {
                    return new world.Location(x,y,true);
                }
            }
        }

    }
    // move pony - changes where a pony is on the grid
    /*
    public void movePony(pony.Pony p, Direction d) {
        // find where the pony is and move it in the direction d
    }

    public char getChar(int x, int y) {
        if(land_data[y][x]==null) {
            return ' ';
        }
        return 'p';
    }

 */
    /** Displays this grid to the user */
    /**
    public void printGrid() {
        for(int y=0; y<sizeY; y++) {
            for(int x=0; x<sizeX; x++) {
                System.out.print(getChar(x,y));
            }
            System.out.println("");
        }
    }
     */
}
