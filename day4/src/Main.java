public class Main {
    public static void main(String[] args) {
        // Make a link contaning a polar bear (idx 0)
        Link lst = new Link("polarbear");

        // Add a link containing a llama (idx 1)
        // ...would be really neat to be able to write
        lst.add("llama");

        // Add a link containing a puppy (idx 2)
        lst.add("puppy");

        // Moke another list
        Link lst2 = new Link(new Point(1,1));
        lst2.add(new Point(2,2));
        lst2.add(new Point(3,3));

        try {
            // What is the programmer trying to do?
            // Check if there is a point --> specifically matches (2,2)
            //                           --> stop if found
            // Is this going to work with this code?
            for (int i = 0; i < 3; i++) {
                System.out.println(lst.get(i));
                // Checks if we've found (2,2)
                if(lst2.get(i).equals(new Point(2,2))) {
                //if(lst2.get(i).equals( lst2.get(1) )) {
                    System.out.println("Found the item");
                    break; // <-- stop the loop
                }
            }
        } catch (CantDoThatException e) {
            throw new RuntimeException(e);
        }
    }
}
