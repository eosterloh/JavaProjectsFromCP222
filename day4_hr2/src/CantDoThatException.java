public class CantDoThatException extends Exception {
    public CantDoThatException(String msg) {
        super(msg); // Make sure that the stack trace an other Exception stuff is setup
    }
}
