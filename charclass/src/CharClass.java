import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**Charclass
 *
 */
public class CharClass implements CharClassI {
    private char[] chars;
    private String name;

    /**
     * char calss constructor
     * @param name the name of the class
     * @param chars the characters
     */
    public CharClass(String name, char[] chars) {
        this.name = name;
        this.chars = chars;
    }
    /**
     * Check if a character is in this character class
     *
     * @param c the character to check
     * @return true if in the character class, otherwise false
     * @throws NotASCIIException if c is out of range
     */
    public boolean match(char c) throws NotASCIIException {
        if (c >127) {
            throw new NotASCIIException(c);
        }
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generate a character class that is the inverse of this
     *
     * @return a character class that matches the inverse
     */
    public CharClassI invert() {
        ArrayList<Character> inverseChars = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            char Character = (char)i;
            boolean found = false;
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == Character) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                inverseChars.add(Character);
            }
        }
        char[] inverseCharsFinal = new char[inverseChars.size()];
        for (int i = 0; i < inverseChars.size(); i++) {
            inverseCharsFinal[i] = inverseChars.get(i);
        }
        return new CharClass(name+"inverted", inverseCharsFinal);
    }

    /**
     * Generate a character class that matches only the characters in both
     * this character class and character class o
     *
     * @param o the other character class
     * @return a character class that matches the intersection
     */
    public CharClassI intersect(CharClassI o) {
        char[] oChars = o.getCharacters();
        ArrayList<Character> result = new ArrayList<>();
        for (int i = 0; i < oChars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == oChars[i]) {
                    result.add(chars[j]);
                }
            }
        }
        Object[] finalchar = result.toArray();
        char[] finalChars = new char[finalchar.length];
        for (int i = 0; i < finalchar.length; i++) {
            finalChars[i] = (char) finalchar[i];
        }
        return new CharClass(name+"intersect", finalChars);
    }

    /**
     * Generate a character class that is matched by this character class or o
     *
     * @param o the other character class
     * @return a character class that matches the union
     */
    public CharClassI union(CharClassI o) {
        char[] charoChars = o.getCharacters();
        Set<Character> result = new HashSet<>(); // Use a Set to automatically handle duplicates

        // Add all characters from this CharClass
        for (char c : this.chars) {
            result.add(c);
        }

        // Add all characters from the other CharClass
        for (char c : charoChars) {
            result.add(c);
        }

        // Convert the set back to a char array
        char[] charfinalChars = new char[result.size()];
        int i = 0;
        for (char c : result) {
            charfinalChars[i++] = c;
        }

        return new CharClass(name + "union", charfinalChars);
    }

    /**
     * The unique characters matched by this CharClass
     *
     * @return the characters this CharClass matches
     */
    public char[] getCharacters() {
        if (chars == null) {
            return null;
        }
        Set<Character> uchars = new HashSet<Character>();
        for (int i = 0; i < chars.length; i++) {
            uchars.add(chars[i]);
        }
        char[] finalresult = new char[uchars.size()];
        int index = 0;
        for (char ch : uchars) {
            finalresult[index] = ch;
            index++;
        }
        return finalresult;
    }

    /**
     * The number of characters in this CharClass
     *
     * @return the number of unique characters this CharClass matches
     */
    public int getSize() {
        return getCharacters().length;
    }

}
