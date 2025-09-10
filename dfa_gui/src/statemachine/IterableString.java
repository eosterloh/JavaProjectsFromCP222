package statemachine;

import java.util.Iterator;

/** A class to wrap a String so that it can be interated over
 *  <p>JAVA strings don't support iteration... I suspect it has to do
 *  with implemantation details in the JRE and String immutability... but
 *  I don't know.</p>
 *  <p>JAVA's String class is "final" so I can't extend it...</p>
 */
public class IterableString implements Iterable<Character> {
    /** The string to iterate over */
    private String string;

    /** Construct the iterable string
     *
     * @param s the backing string instance
     */
    public IterableString(String s) {
        string = s;
    }

    /** Get the backing string back
     *
     * @return the underlying string
     */
    public String toString() {
        return string;
    }

    /** Get the iterator
     *
     * @return an iterator for this string
     */
    @Override
    public Iterator<Character> iterator() {
        return new StrIterator();
    }

    /** Inner class for the iterator on string instances
     *
     */
    private class StrIterator implements Iterator<Character> {
        /** the current index reached */
        private int idx=0;

        /** Is there a next character?
         *
         * @return true if there is another character to retrieve
         */
        @Override
        public boolean hasNext() {
            return idx<string.length();
        }

        /** Get the next character
         *
         * @return the next character
         */
        @Override
        public Character next() {
            return string.charAt(idx++);
        }
    }
}
