package fileaccess;

import statemachine.DFATraverser;
import statemachine.IterableString;
import statemachine.State;
import statemachine.StateI;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/** Generates DFAs for accepting/rejecting character sequences
 * <p>Input file should contain one sequence per line,</p>
 */
public class DictionaryGen implements StateMachineGenI<Character> {
    private int linenum;
    /** Default constructor */
    public DictionaryGen() {linenum = 0; }

    /**
     * Instantiates a state machine from the contents of a file
     * <p>All states in the state machine should be frozen when this function returns.</p>
     *
     * @param f the file containing the machine description
     * @return The start state of the machine
     * @throws InvalidStateMachineException when there are errors in the description
     * @throws FileNotFoundException        when the file cannot be read
     * @throws IOException                  when the underlying libraries throw IOException
     */
    @Override
    public StateI<Character> read(File f) throws InvalidStateMachineException, FileNotFoundException, IOException {
        InputStream in = new FileInputStream(f);
            ArrayList<String> words = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while(line != null) {
                line = line.strip();
                if(!line.isBlank() && !line.startsWith("#")) {
                    words.add(line);
                }
                line = br.readLine();
            }
            // Sort the words from the file by length
            words.sort(new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.length()-s2.length();
                }
            });

            StateI<Character> start = new State<>(null);
            DFATraverser<Character> traverser = new DFATraverser<>();
            for(String w:words) {
                linenum++;
                if(traverser.traverse(start, new IterableString(w))) {
                    traverser.getFinalState().setAccept(true);
                    continue;
                }
                StateI<Character> cur = traverser.getFinalState();
                int idx = traverser.getNumMatched();
                // process the intermediate characters
                for(int i=idx; i<w.length(); i++) {
                    StateI<Character> nxt = new State<>(null);
                    cur.addTransition(w.charAt(i), nxt);
                    cur = nxt;
                }
                cur.setAccept(true);
            }
            return start;
    }

    public int getLineNumber(){
        return linenum;
    }
}
