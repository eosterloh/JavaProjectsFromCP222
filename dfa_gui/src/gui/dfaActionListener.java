package gui;

import fileaccess.DFAGen;
import fileaccess.InvalidStateMachineException;
import statemachine.StateI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class dfaActionListener implements ActionListener {
    public Gui gui;
    private DFAGen dfa;
    private JFileChooser fileChooser;
    private GridBagConstraints gc;
    private StateI<Character> s;

    public dfaActionListener(Gui gui) {
        this.gui = gui;
        this.dfa = new DFAGen();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("DFA Files", "dfa");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File("data"));
        int returnValue = fileChooser.showOpenDialog(gui);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                s = dfa.read(fileChooser.getSelectedFile());
                gui.setTitle(fileChooser.getSelectedFile().getName());
                gui.getCheckButton().setEnabled(true);
                gui.getEnteredtxt().setEditable(true);
                gui.getEnteredtxt().setEnabled(true);
                gui.getLastchecked().setText("");
                gui.getEnteredtxt().setText("");
                gui.setStartState(s);
            } catch (InvalidStateMachineException ex) {
                showError(ex.getStateMachineMessage(), "on line number: "+ dfa.getLineNumber(), "in file "+fileChooser.getSelectedFile().getName());
                gui.setTitle("Select File");
            } catch (IOException ex) {
                showError(ex.getLocalizedMessage(), "","");
            }
        }
    }

    public void showError(String message1, String message2, String message3) {
        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();
        JFrame error = new JFrame();
        error.setLayout(gb);
        error.setTitle("Error");
        error.setLocation(600,400);
        error.setSize(300,200);
        error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.getEnteredtxt().setEditable(false);
        gui.getEnteredtxt().setEnabled(false);
        gui.getCheckButton().setEnabled(false);

        JLabel num1 = new JLabel(message1);
        gc.gridx = 0;
        gc.gridy = 0;
        error.add(num1,gc);

        JLabel num2 = new JLabel(message2);
        gc.gridx = 0;
        gc.gridy = 1;
        error.add(num2,gc);
        JLabel num3 = new JLabel(message3);
        gc.gridx = 0;
        gc.gridy = 2;
        error.add(num3,gc);
        error.setVisible(true);
    }

    public StateI<Character> getState(){
        return s;
    }

}
