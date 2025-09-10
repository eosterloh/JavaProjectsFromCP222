package fileaccess;

import pony.EarthPony;
import pony.Pegases;
import pony.Pony;
import pony.UnicornPony;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** Reads .pny files */
public class PnyReader {

    public ArrayList<Pony> read(File f) throws BadPnyFileExeception,IOException {
        // Make a list of stuff to return
        ArrayList<Pony> ponies = new ArrayList<>();

        // Open the file for reading
        BufferedReader reader = new BufferedReader(new FileReader(f));

        // Read the lines one at a time
        // Foreach line
        String line = reader.readLine();
        while(line!=null) {
            //      Figure out the kind of line I have
            //       Kinds of lines... ignorable, earth, unicorn, pegusas
            // ignorable lines...
            if(line.startsWith("#") || line.isBlank()) {
                // Do nothing with the line... get the next line
                line = reader.readLine();
                continue;
            }

            Pony curPony = null; // The variable for the pony we're constructing
            //      Separate into the appropriate parts
            String[] parts = line.split(",");
            //      Instaniate the pony based on the line
            if(parts[0].equals("e")) {
                // earth pony
                String name = parts[1];
                String color = parts[2];
                int strength = Integer.parseInt(parts[3]);
                curPony = new EarthPony(name, color, strength);
            }
            if(parts[0].equals("p")) {
                // pegasus pony - 4 or 5 parts
                String name = parts[1];
                String color = parts[2];
                int wingspan = Integer.parseInt(parts[3]);
                // sometimes the array isn't this long...
                //boolean pacifist = parts[4].equals("+");
                boolean pacifist = false;
                if(parts.length==5) {
                    pacifist = parts[4].equals("+");
                }
                curPony = new Pegases(name, color, wingspan, pacifist);
                // Could do the add here... Danielle decided not to and
                // put it after the ifs instead.
            }
            if(parts[0].equals("u")) {
                // unicorn pony
                String name = parts[1];
                String color = parts[2];
                String horn = parts[3];
                curPony = new UnicornPony(name, color, horn);
            }
            //      add to the list of stuff to return
            if(curPony != null) {
                ponies.add(curPony);
            } else {
                throw new BadPnyFileExeception("Error processing .pny file");
            }

            // Read for the next line
            line = reader.readLine();
        }

        // return the completed list
        return ponies;
    }
}
