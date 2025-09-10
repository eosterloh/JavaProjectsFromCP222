package fileaccess;

import statemachine.State;
import statemachine.StateI;
import statemachine.Transition;
import statemachine.TransitionI;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

/** Reads .dfa files */
// You can't change the class declaration or read method declaration... but
    // you can introduce whatever instance variables and methods
    // you'd like when defining the class and method.
public class DFAGen implements StateMachineGenI<Character> {
    private int linenum;
    public DFAGen() {
        linenum = 0;
    }
    /**
     * Instantiates a state machine from the contents of a file
     * <p>All states in the state machine should be frozen when this function returns.</p>
     *
     * @param f the file containing the machine description
     * @return The start state of the machine
     * @throws InvalidStateMachineException when there are errors in the description
     * @throws FileNotFoundException        when the file cannot be found/read
     * @throws IOException                  when the underlying libraries throw IOException
     */
    @Override
    public StateI<Character> read(File f) throws InvalidStateMachineException, FileNotFoundException, IOException {
        // You need to implement this method.
        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<StateI<Character>> states = new ArrayList<>();
        String readline;
        int acceptstatecounter = 0;
        int startstatecounter = 0;

        while ((readline = br.readLine()) != null) {
            lines.add(readline);
        }
        linenum = 0;
        for (String line : lines) {
            linenum++;
            //work on this
            //REMOVE EACH EMPTY SPLIT
            String[] split = line.trim().split("\\s+");
            if(split.length == 0)
                continue;
            else if (split[0].isEmpty())
                continue;
            if (split[0].charAt(0) == '#')
                continue;
            if (split[0].equals("s")){
                //logic with what to do with state lines
                if(split.length <2)
                    throw new InvalidStateMachineException(f, linenum,"State line missing the state label");
                for (int i =0; i < states.size(); i++)
                    if (split[1].equals(states.get(i).getLabel()))
                        throw new InvalidStateMachineException(f, linenum,"State with label " + states.get(i).getLabel() + " already exists");
                if (split.length == 2){
                    StateI<Character> s = new State<Character>(split[1]);
                    states.add(s);
                }
                if (split.length == 3) {
                    String[] middleSplit = split[2].split(",");
                    if ((middleSplit.length > 1) && !(split[2].contains("start")&&split[2].contains("accept")))
                        throw new InvalidStateMachineException(f, linenum,"Unsupported attribute "+ middleSplit[1]);
                    if ((middleSplit.length > 1) && (middleSplit[1].equals("accept") && (middleSplit[0].equals("start")))) {
                        StateI<Character> s = new State<Character>(split[1]);
                        s.setAccept(true);
                        acceptstatecounter++;
                        startstatecounter++;
                        states.add(0, s);
                    } else if (middleSplit.length > 1 && (middleSplit[0].equals("accept") && (middleSplit[1].equals("start")))) {
                        StateI<Character> s = new State<Character>(split[1]);
                        s.setAccept(true);
                        acceptstatecounter++;
                        startstatecounter++;
                        states.add(0, s);
                    } else if (middleSplit.length == 1 && middleSplit[0].equals("accept")) {
                        StateI<Character> s = new State<Character>(split[1]);
                        s.setAccept(true);
                        acceptstatecounter++;
                        states.add(s);
                    } else if (middleSplit.length == 1 && middleSplit[0].equals("start")) {
                        StateI<Character> s = new State<Character>(split[1]);
                        startstatecounter++;
                        states.add(0, s);
                    } else {
                        StateI<Character> s = new State<Character>(split[1]);
                        states.add(s);
                    }
                }
                if (split.length > 3){
                    throw new InvalidStateMachineException(f, linenum,"Stata line contains too many fields");
                }
                if(startstatecounter > 1)
                    throw new InvalidStateMachineException(f, linenum,"Duplicate start state declared");
            }else if (split[0].equals("t")){
                //logic for transitions
                //adding the transition to the source state
                if (split.length < 4){
                    throw new InvalidStateMachineException(f, linenum,"Insufficient fields for transition line");
                }
                String srcstate= split[1];
                String deststatename = split[2];
                String symbol= split[3];
                if (symbol.length()>1){
                    throw new InvalidStateMachineException(f, linenum,"Transition symbol is not a single character");
                }
                StateI<Character> deststate = null;
                for (StateI<Character> sts : states ){
                    if (sts.getLabel().equals(deststatename)){
                        deststate = sts;
                        break;
                    }
                }
                if (deststate == null){
                    throw new InvalidStateMachineException(f, linenum,"Destination (" + deststatename + ") for the transition is unknown");
                }
                int counter = 0;
                for (StateI<Character> sts : states ){
                    if (sts.getLabel().equals(srcstate)){
                        for (TransitionI trs: sts.getTransitions())
                            if (symbol.charAt(0) == (char)trs.getSymbol())
                                throw new InvalidStateMachineException(f, linenum, "Transition from " +  srcstate +" on " + symbol + " already exists");
                        sts.addTransition(symbol.charAt(0), deststate);
                        counter++;
                        break;
                    }
                }

                if (counter == 0){
                    throw new InvalidStateMachineException(f, linenum,"Source (" + srcstate + ") for the transition is unknown");
                }

                if (split.length > 4){
                    throw new InvalidStateMachineException(f, linenum,"Too many fields for transition line");
                }
            }else if (split[0].substring(0,1).equals("#")){
                continue;
            }else{
                throw new InvalidStateMachineException(f, linenum,"Line is not blank, a state, a transition, or a comment");
            }

        }
        if (startstatecounter == 0){
            throw new InvalidStateMachineException(f, linenum,"No start state found before EOF");
        }
        return states.get(0);


    }

    public int getLineNumber(){
        return linenum;
    }
}
