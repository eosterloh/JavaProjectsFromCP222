/** Signals a char/byte is out of range */
public class NotASCIIException extends Exception {
    /** Creates for a bad char
     *
     * @param c the out of range char
     */
    public NotASCIIException(char c) {
        super(c+" is outside of the 7-bit ASCII range.");
    }

    /** Creates for a bad byte
     *
     * @param b the out of range byte
     */
    public NotASCIIException(byte b) {
        super(b+" is outside of the 7-bit ASCII range.");
    }
}
