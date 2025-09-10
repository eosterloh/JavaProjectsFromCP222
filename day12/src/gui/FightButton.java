package gui;

import javax.swing.*;

public class FightButton extends JButton {

    public FightButton() {
        super("Fight!");
        setFont(Config.txt);
        // Configure the button with the thing it is supposed to do...
        // Architecturally not great to instantiate here, needs the selectors...
        // addActionListener(new FightButtonAction() );
    }

}
