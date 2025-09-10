/** Program to generate some sequential numbers
*/
public class RunSample1 {
    
    public static void main(String[] args) {
        // Create a new instance
        RecallI r = new Recall();

        // Try filling an array from 0 to 27 counting by 3
        int[] a = new int[10];
        r.fillArray(a, -3);

        // Print out the array contents
        for(int i=0; i<a.length; i++) {
            System.out.println("a["+i+"] -> "+a[i]);
        }

        return;
    }
}
