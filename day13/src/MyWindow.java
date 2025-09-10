import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {
    JTextField outTxt;
    JTextField inTxt;

    public MyWindow() {
        super();

        // Make widgets
        // IO labels
        JLabel inLbl = new JLabel("Input:");
        JLabel outLbl = new JLabel("Output:");

        inTxt = new JTextField();
        outTxt = new JTextField();

        JButton encBtn = new JButton("encrypt");

        // Place on Window
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);
        setLayout(gb);

        // Add inLbl
        gc.gridx = 0;
        gc.gridy = 0;
        gb.setConstraints(inLbl, gc);
        add(inLbl);
        // Add outLbl
        gc.gridx = 0;
        gc.gridy = 1;
        gb.setConstraints(outLbl, gc);
        add(outLbl);
        // Add button
        gc.gridx = 2;
        gc.gridy = 2;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.SOUTHEAST;
        gb.setConstraints(encBtn, gc);
        add(encBtn);
        gc.weighty = 0;
        // Add inTxt
        gc.gridx = 1;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gb.setConstraints(inTxt, gc);
        add(inTxt);
        // Add outTxt
        gc.gridx = 1;
        gc.gridy = 1;
        gb.setConstraints(outTxt, gc);
        add(outTxt);

        // Make the action listener
        EncryptAction ea = new EncryptAction(this);
        // Then connect to stuff
        encBtn.addActionListener(ea);
        inTxt.addActionListener(ea);

        // Layout the window
        pack();
    }

    public void setOutput(String out) {
        outTxt.setText(out);
    }
    public String getInput() {
        return inTxt.getText();
    }
}
