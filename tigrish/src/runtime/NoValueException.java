package runtime;

/** Exception to be thrown when referencing a variable that does not have a value yet */
public class NoValueException extends Throwable {
    /** Create a new instance */
    public NoValueException() {
        super();
    }
}
