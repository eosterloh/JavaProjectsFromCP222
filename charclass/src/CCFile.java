import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads the given file
 */
public class CCFile implements CCFileI {

    /**
     * Construcrorrr
     */
    public CCFile() {

    }

    /** Read in a file describing character classes
     *
     * @param infile the file to read describing named classes
     * @return A lookup based on the file
     * @throws InvalidLineException if the line does not contain a name and at least one character/named character class or redefines an existing character class
     * @throws NotASCIIException if any character added to a CharClass is out of range
     * @throws IOException the IOException thrown by any underlying JAVA library
     */
    public CharClassLookupI readCCFile(File infile) throws IOException, NotASCIIException, InvalidLineException {
        BufferedReader in = new BufferedReader(new FileReader(infile));
        ArrayList<String> lines = new ArrayList<>();
        CharClassLookup lookup = new CharClassLookup();
        lookup.addCC("space", " ");
        lookup.addCC("tab", "\t");
        lookup.addCC("newline", "\n");
        String curline = "";
        while((curline = in.readLine()) != null) {
            lines.add(curline);
        }
        //must write something that reads the beginning and end of each CC name, if they are both colons, search the lookup for this thing
        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).isEmpty()){
                continue;
            }else if (lines.get(i).substring(0, 1).equals("#")) {
                continue;
            }
            //get name
            String[] split = lines.get(i).split("\\s+");

            if (split.length < 2) {
                throw new InvalidLineException(i+1, lines.get(i));
            }
            String name = split[0];

            //TODO Fix this
            if (name.equals("space")|| name.equals("tab")|| name.equals("newline")) {
                throw new InvalidLineException(i, lines.get(i));
            }
            for (String name2 : lookup.getNames()) {
                if (name.equals(name2)) {
                    throw new InvalidLineException(i, lines.get(i));
                }
            }
            //get characters
            //make a charcter class containing the one that is refrenced.
            //use union to adjoin them. see file #1
            //String[] chars = new String[lines.get(i).length()-counter+1];
            if (split.length == 2) {
                lookup.addCC(name, split[1]);
            }else if (split.length > 2) {
                String chars = "";
                for (int j = 1; j < split.length; j++) {
                    if (split[j].equals(":space:")) {
                        chars = " ";
                    } else if (split[j].equals(":tab:")) {
                        chars = "\t";
                    } else if (split[j].equals(":newline:")) {
                        chars = "\n";
                    } else if ((split[j].charAt(0) == ':') && (split[j].charAt(split[j].length() - 1) == ':') && (split[j].length() > 2)) {
                        try {
                            String thename = split[j].substring(1, split[j].length() - 1);
                            char[] thechars = lookup.getCC(thename).getCharacters();
                            chars += new String(thechars);//thx itelliJ
                        } catch (NullPointerException e) {
                            e.getStackTrace();
                        }
                    } else {
                        chars += split[j];
                    }
                }
                lookup.addCC(name, chars);
            }

        }
        //return it
        return lookup;
    }
}
