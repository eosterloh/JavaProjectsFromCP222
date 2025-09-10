import java.util.Set;

/** Interface for objects that store named CharClassIs
 *  <p>Names must be unique; two character classes in the lookup cannot share
 *  a name.</p>
 */
public interface CharClassLookupI {
    /** Get all of the character class names in this lookup instance
     *
     * @return the set of names
     */
    public Set<String> getNames();

    /** Get a specific character class using it's name
     *
     * @param name the name of the character class to retrieve
     * @return the character class instance or null if none matches
     */
    public CharClassI getCC(String name);

    /** Add a new character class that matches the characters in chars
     *  <p>If the name already exists in the lookup, change the name to be
     *  associated with the character class containing the characters in chars
     *  instead of the character class previously associated with the name.</p>
     *  <p>If name or chars is null, calling this method should do nothing.</p>
     * @param name  the name to assocate with the character class
     * @param chars the characters to place in the character class
     * @throws NotASCIIException when one of the chars in String isn't in the range 0 to 127
     */
    public void addCC(String name, String chars) throws NotASCIIException;

    /** Add an existing character class to the lookup
     * <p>If the name already exists in the lookup, change the name to be
     *  associated with the character class cc instead of the character class
     *  previously associated with the name.</p>
     *  <p>If name or cc is null, calling this method should do nothing.</p>
     * @param name
     * @param cc
     */
    public void addCC(String name, CharClassI cc);

}