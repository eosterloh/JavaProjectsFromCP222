package gui;

import pony.Pony;

import javax.swing.*;
import java.util.ArrayList;


public class PonySelector extends JComboBox<Pony> {
    public PonySelector() {
        super();
        setFont(Config.txt);
    }

    public void setPonies(ArrayList<Pony> ponies) {
        // Remove all the existing items
        this.removeAllItems();

        // add the ones from the array
        for(Pony p:ponies) {
            this.addItem(p);
        }
    }
}
