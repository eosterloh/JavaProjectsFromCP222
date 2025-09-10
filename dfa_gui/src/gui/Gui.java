package gui;

import statemachine.StateI;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private GridBagLayout gb;
    private GridBagConstraints gc;

    private JButton dfabutton;
    private JButton txtbutton;
    private JButton checkbutton;

    private JTextField enteredtxt;
    private JTextField lastchecked;

    private txtActionListener txtAL;
    private dfaActionListener dfaAL;
    private checkActionListener checkAL;

    private StateI<Character> startState;



    public Gui(){
        setTitle("Select File");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocation(600,400);
        gb = new GridBagLayout();
        JPanel panel = new JPanel();;
        gc = new GridBagConstraints();
        panel.setLayout(gb);
        dfaAL = new dfaActionListener(this);
        txtAL = new txtActionListener(this);
        checkAL = new checkActionListener(this);


        dfabutton = new JButton(".dfa");
        gc.gridx = 3;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(10, 10, 10, 10);
        dfabutton.addActionListener(dfaAL);
        panel.add(dfabutton, gc);


        txtbutton = new JButton(".txt");
        gc.gridx = 5;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(10, 10, 10, 10);
        txtbutton.addActionListener(txtAL);
        panel.add(txtbutton, gc);

        checkbutton = new JButton("Check");
        gc.gridx = 5;
        gc.gridy = 4;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(10, 10, 10, 10);
        checkbutton.addActionListener(checkAL);
        checkbutton.setEnabled(false);
        panel.add(checkbutton, gc);

        enteredtxt = new JTextField(30);
        gc.gridx = 3;
        gc.gridy = 1;
        gc.gridwidth = 3;
        gc.weightx = .5;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10, 0, 5, 10);
        enteredtxt.setEditable(false);
        enteredtxt.setEnabled(false);
        enteredtxt.addActionListener(checkAL);
        panel.add(enteredtxt, gc);


        lastchecked = new JTextField(30);
        gc.gridx = 3;
        gc.gridy = 2;
        gc.gridwidth = 3;
        gc.weightx = .5;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(5, 0, 10, 10);
        lastchecked.setEditable(false);
        lastchecked.setEnabled(false);
        panel.add(lastchecked, gc);


        //Maxine for tigrish
        //and should cursor be able to go in there
        JLabel dfawords = new JLabel("Enter text:");
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridheight = 1;
        gc.weightx = 0.0;
        gc.weighty = 0.0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(10, 10, 10, 0);
        panel.add(dfawords, gc);


        JLabel lastcheckedtxt = new JLabel("Last Checked:");
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridheight = 1;
        gc.weightx = 0.0;
        gc.weighty = 0.0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(10, 10, 10, 0);
        panel.add(lastcheckedtxt, gc);

        getContentPane().add(panel);
    }

    public void textSetter(String text){
        //some handling of how to set the text for the last dfa checked
        lastchecked.setText(text);
    }

    public GridBagConstraints getGc(){
        return gc;
    }
    public JTextField getLastchecked(){
        return lastchecked;
    }

    public JTextField getEnteredtxt(){
        return enteredtxt;
    }

    public JButton getCheckButton(){
        return checkbutton;
    }
    public void setLastCheckedG(){
        lastchecked.setText(enteredtxt.getText());
        lastchecked.setForeground(Color.GREEN);
        Font currentFont = lastchecked.getFont();
        Font newFont = new Font(currentFont.getName(), Font.BOLD, currentFont.getSize());
        lastchecked.setFont(newFont);
        lastchecked.setDisabledTextColor(Color.GREEN);
        checkbutton.setEnabled(true);
    }

    public void setLastCheckedB(){
        lastchecked.setText(enteredtxt.getText());
        Font currentFont = lastchecked.getFont();
        Font newFont = new Font(currentFont.getName(), Font.BOLD, currentFont.getSize());
        lastchecked.setFont(newFont);
        lastchecked.setDisabledTextColor(Color.RED);
    }

    public StateI<Character> getStartState(){
        return startState;
    }

    public void setStartState(StateI<Character> s){
        startState = s;
    }
}
