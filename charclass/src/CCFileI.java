import java.io.File;
import java.io.IOException;

/** Interface for things that read/write descriptions of character classes */
public interface CCFileI {

    /** Read in a file describing character classes
     *
     * @param infile the file to read describing named classes
     * @return A lookup based on the file
     * @throws InvalidLineException if the line does not contain a name and at least one character/named character class or redefines an existing character class
     * @throws NotASCIIException if any character added to a CharClass is out of range
     * @throws IOException the IOException thrown by any underlying JAVA library
     */
    public CharClassLookupI readCCFile(File infile) throws IOException, NotASCIIException, InvalidLineException;
}