package pony;

/** Exception throw to indicate that the pony.Pony refuses to do
 * what we've asked it to do
 */
public class RefusalException extends Exception {
    /** Constructs a new refusal
     *
     * @param msg the message about why it was refused
     */
    public RefusalException(String msg) {
        super(msg);
    }
}
