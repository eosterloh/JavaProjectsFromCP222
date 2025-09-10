package fileaccess;

import lines.LineI;
import lines.NoNextInstructionException;
import lines.NoSuchLabelException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/** Interface for an object that writes .hmmm files */
public interface HmmmWriterI {
    /**
     * Writes a .hmmm file from a list of lines
     *
     * @param f           file to write to
     * @param labelLookup mapping of label to corrasponding line
     * @param lines       the lines to be written
     * @throws NoSuchLabelException if an unknown label occurs
     * @throws NoNextInstructionException if a label cannot be resolved to an address
     * @throws BadFileTypeException if the file suffix is not .hmmm
     * @throws IOException          if the underlying stream has an IO problem
     */
    public void write(File f, HashMap<String, LineI> labelLookup, LineI lines) throws NoNextInstructionException, NoSuchLabelException, BadFileTypeException, IOException;
}
