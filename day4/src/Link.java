/** Represents a link in a linked list
 *  ...only need to travers forwards (singly linked rather than
 *  doubly linked list)
 * */

public class Link {
    // Attributes
    private Link   next; // Next link in the list
    private Object value; // Value at this link

    // Constructor - It's a method but also a little special
    public Link(Link next, Object value) {
        this.next  = next;
        this.value = value;
    }

    public Link(Object value) {
        this.value = value;
    }

    // Methods
    public Link next() {
        return next;
    }

    public Object getValue() {
        return value;
    }

    public Object get(int idx) throws CantDoThatException {
        if(idx==0) {
            return value;
        }
        if (next==null) {
            throw new CantDoThatException("Ran off the list");
        }
        return next.get(idx-1);
    }
    /** An add method so that Danielle can write the Main method she really really
     *  wants to write...
     *  Link lst = new Link("polarbear");
     *  lst.add("llama");
     *  list.add("puppy");
     * @param o the thing to add to the end of the list
     */
    public void add(Object o) {
        // Base case:
        // If there no next, add a link with the object
        if(next == null) {
            next = new Link(o);
            return;
        }
            // Recursive case:
            // Otherwise, find the end of the list and link there
            next.add(o);

    }
}
