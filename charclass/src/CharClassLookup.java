import java.util.*;

/** Interface for objects that store named CharClassIs
 *  <p>Names must be unique; two character classes in the lookup cannot share
 *  a name.</p>
 */
public class CharClassLookup implements CharClassLookupI {
    private HashMap<String, CharClass> charClasses;

    /**
     * Charclass lookup consructor
     */
    public CharClassLookup() {
        charClasses = new HashMap<>();
    }
    /**
     * Get all of the character class names in this lookup instance
     *
     * @return the set of names
     */
    public Set<String> getNames() {
        Set<String> names = new HashSet<>();
        Set<String> keys = charClasses.keySet();
        for (String name : keys) {
            names.add(name);
        }
        return names;
    }

    /**
     * Get a specific character class using it's name
     *
     * @param name the name of the character class to retrieve
     * @return the character class instance or null if none matches
     */
    public CharClassI getCC(String name) {
        if (charClasses.containsKey(name)){
            return charClasses.get(name);
        }
        return null;
    }

    /**
     * Add a new character class that matches the characters in chars
     * <p>If the name already exists in the lookup, change the name to be
     * associated with the character class containing the characters in chars
     * instead of the character class previously associated with the name.</p>
     * <p>If name or chars is null, calling this method should do nothing.</p>
     *
     * @param name  the name to assocate with the character class
     * @param chars the characters to place in the character class
     * @throws NotASCIIException when one of the chars in String isn't in the range 0 to 127
     */
    public void addCC(String name, String chars) throws NotASCIIException {
        char[] charsArray = new char[chars.length()];
        for(int i=0; i<chars.length(); i++){
            charsArray[i] = chars.charAt(i);
        }
        if (getNames().contains(name)){
            charClasses.remove(name);
            CharClass charClass = new CharClass(name, charsArray);
            charClasses.put(name, charClass);
        }else {
            CharClass charClass = new CharClass(name, charsArray);
            charClasses.put(name, charClass);
        }
    }

    /**
     * Add an existing character class to the lookup
     * <p>If the name already exists in the lookup, change the name to be
     * associated with the character class cc instead of the character class
     * previously associated with the name.</p>
     * <p>If name or cc is null, calling this method should do nothing.</p>
     *
     * @param name name of charclass
     * @param cc THe charclass
     */
    public void addCC(String name, CharClassI cc) {
        CharClass charc = (CharClass)cc;
        boolean found = false;
        for (String names : getNames()) {
            if (names.equals(name)){
                found = true;
            }
        }
        if (found){
            charClasses.remove(getCC(name));
            charClasses.put(name, charc);
        }else {
            charClasses.put(name, charc);
        }
    }
}