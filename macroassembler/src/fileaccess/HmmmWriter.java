package fileaccess;

import lines.LineI;
import lines.NoNextInstructionException;
import lines.NoSuchLabelException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * supposed to write but doesn't work
 */
public class HmmmWriter implements HmmmWriterI {

    /**
     * Writes a .hmmm file from a list of lines
     *
     * @param f           file to write to
     * @param labelLookup mapping of label to corrasponding line
     * @param lines       the lines to be written
     * @throws NoSuchLabelException       if an unknown label occurs
     * @throws NoNextInstructionException if a label cannot be resolved to an address
     * @throws BadFileTypeException       if the file suffix is not .hmmm
     * @throws IOException                if the underlying stream has an IO problem
     */
    public void write(File f, HashMap<String, LineI> labelLookup, LineI lines) throws NoNextInstructionException, NoSuchLabelException, BadFileTypeException, IOException {

        if(!(f.getName().endsWith(".hmmm"))) {
            throw new BadFileTypeException(f, ".hmmm");
        }

        for(String label : labelLookup.keySet()) {
            if (labelLookup.get(label) == null) {
                throw new NoSuchLabelException(labelLookup.get(label));
            }
            if(labelLookup.get(label).getLineLocation().getLinenum() >= 1000){
                throw new NoSuchLabelException(labelLookup.get(label));
            }
        }

        FileWriter fileWriter = new FileWriter(f);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        LineI curline = lines;
        while(curline != null) {
            writer.write(curline.getOutputText(labelLookup));
            writer.newLine();
            curline = curline.getNextLine();
        }
        writer.close();
    }
}
