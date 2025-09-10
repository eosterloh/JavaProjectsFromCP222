/** If the algorithm is correct, this exception should never be thrown */
public class DoneBrokeException extends Error {
    public DoneBrokeException(String s) {
        super(s);
    }
}
