/** Interface for character classes/sets
 *  <p>CharClassI instances encapsulate logic associated with matching individual
 *  characters.</p>
 *  <p>Implementations are only going to concern themselves with 7-bit
 *  ASCII characters. We'll talk more about binary data representation in
 *  CP275... it's sufficient for now to know that internally, all chars
 *  are numbers and ASCII chars are in the range 0 through 127 and can be
 *  represented using an 8-bit signed integer (byte)... Knowing the range
 *  will probably help you implementers with the invert method.</p>
 */
public interface CharClassI {
    /** Check if a character is in this character class
     *
     * @param c the character to check
     * @return true if in the character class, otherwise false
     * @throws NotASCIIException if c is out of range
     */
    public boolean match(char c) throws NotASCIIException;

    /** Generate a character class that is the inverse of this
     *
     * @return a character class that matches the inverse
     */
    public CharClassI invert();

    /** Generate a character class that matches only the characters in both
     * this character class and character class o
     * @param o the other character class
     * @return a character class that matches the intersection
     */
    public CharClassI intersect(CharClassI o);

    /** Generate a character class that is matched by this character class or o
     *
     * @param o the other character class
     * @return a character class that matches the union
     */
    public CharClassI union(CharClassI o);

    /** The unique characters matched by this CharClass
     *
     * @return the characters this CharClass matches
     */
    public char[] getCharacters();

    /** The number of characters in this CharClass
     *
     * @return the number of unique characters this CharClass matches
     */
    public int getSize();
}