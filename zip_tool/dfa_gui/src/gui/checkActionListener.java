package gui;

import statemachine.DFATraverser;
import statemachine.IterableString;
import statemachine.State;
import statemachine.StateI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class checkActionListener implements ActionListener {
    private Gui gui;


    public checkActionListener(Gui gui) {
        this.gui = gui;
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Logic for checking in the entered text is valid in the DFA
        String text = gui.getEnteredtxt().getText();
        //make a DFA matcher?? not sure what im allowed to/supposed to do
        //basically what this does is if the string in entered text is accpepted by dfa
        //text should turn green I think. If it doesn't text turns red
        //after that, lastchecked is set to the enteredtext.
        DFATraverser<Character> m = new DFATraverser<>();
        StateI<Character> ss = gui.getStartState();
        if (m.isAccepted(ss, new IterableString(text))){
            gui.setLastCheckedG();
        }else{
            gui.setLastCheckedB();
        }
    }
}
