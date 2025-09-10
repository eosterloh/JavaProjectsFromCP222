import java.io.IOException;
/**
A JAVA interface describing the required methods and functionality for the
first CS2 assignment.
*/
public interface RecallI {
    /** Set all values in arr by counting up
    <p>If inc is positive, the array should be filled in from 0 counting up by
        inc. e.g. inc=3 --&gt; 0, 3, 6, ...
    <p>If inc is negative, the array should be filled in from the highest
        positive number and count down by inc, with 0 in the last index.    
        e.g. inc=-3 --&gt; ..., 6, 3, 0
    @param arr An array to set the values of
    @param int An amount to count up by
    */
    public void fillArray(int[] arr, int inc);
    
    /** Sets the filename to use for later file operations
    <p>For non-null Strings, this will update the filename to use when 
        findLineWith is called. This method may be called many times.
    <p>If setFilename is called with a null String, the filename to use 
        when findLineWith is called should not be changed.
    @param fname a filename/path, given as a String, for use in file operations
    */
    public void setFilename(String fname);

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
    public String findLineWith(String substr, boolean reset) throws UnsetFilenameException, IOException;
}
