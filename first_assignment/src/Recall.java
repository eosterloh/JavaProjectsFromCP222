import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Recall class that implements recallI
 */
public class Recall implements RecallI{
    private int count;
    public String filename = "";

    /**
     * Fills an array
     * @param arr An array to set the values of
     * @param inc the increment it goes up by
     */
    public void fillArray(int[] arr, int inc){

        if (inc >= 0) {
            int counter = 0;
            for (int i = 0; i < arr.length; i++) {
                arr[i] = counter;
                counter += inc;
            }
        }
        else {
            int counter = Math.abs(inc)*(arr.length-1);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = counter;
                counter = counter - Math.abs(inc);
            }
        }
    }

    /** Sets the filename to use for later file operations
     <p>For non-null Strings, this will update the filename to use when
     findLineWith is called. This method may be called many times.
     <p>If setFilename is called with a null String, the filename to use
     when findLineWith is called should not be changed.
     @param fname a filename/path, given as a String, for use in file operations
     */
    public void setFilename(String fname){
       if (fname == null){
           return;
       }
       count = 0;
       filename = fname;
    }
    /** Returns the text of the next line containing a specific string
     <p>If the filename has been successfully updated since the last time
     findLineWith was called, the search should start from the begining
     of the file. This happens even if reset is set to false and the updated
     filename is equal to the previous filename.
     @param substr the text to look for
     @param reset if true, start over from the beginning of the file
     @return Return the text of the matching line or null if a matching line was not found before the end of the file.
     @throws UnsetFilenameException if called while the filename in this instance is unset (there has been no call to setFilename with a non-null String)
     @throws IOException all IO exceptions from JAVA's standard library should not be caught and should pass through to the caller.
     */
    public String findLineWith(String substr, boolean reset) throws UnsetFilenameException, IOException {
        String line = "";
        ArrayList<String> lines = new ArrayList<>();
        if(filename.equals("")) {
            reset = false;
            throw new UnsetFilenameException();
        }

        BufferedReader br = new BufferedReader(new FileReader(filename));
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        if (reset) {
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < (lines.get(i).length() - substr.length()); j++) {
                    if (lines.get(i).substring(j, j + substr.length()).equals(substr)) {
                        count = i;
                        return lines.get(i);
                    }
                }
            }
        }else{
            for (int i = count+1; i < lines.size(); i++) {
                for (int j = 0; j < (lines.get(i).length() - substr.length()); j++) {
                    if (lines.get(i).substring(j, j + substr.length()).equals(substr)) {
                        count = i;
                        return lines.get(i);
                    }
                }
            }
        }

        return null;
    }
}
