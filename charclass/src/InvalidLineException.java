public class InvalidLineException extends Exception {
    /** Creates an instance for a specific line
     *
     * @param num  which line in the file
     * @param line the line causing the exception
     */
    public InvalidLineException(int num, String line) {
        super("Invalid line: "+line);
    }
}
