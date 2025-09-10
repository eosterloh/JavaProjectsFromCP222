package gui;

import pony.Pony;
import pony.RefusalException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightActionListener implements ActionListener {
    PonySelector attackerSelect;
    PonySelector attackeeSelect;

    public FightActionListener(PonySelector psa, PonySelector psb) {
        attackerSelect = psa;
        attackeeSelect = psb;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Get attacker
        Pony attacker = (Pony)attackerSelect.getSelectedItem();
        // Get attackee
        Pony attackee = (Pony)attackeeSelect.getSelectedItem();
        // TODO: play animation

        // execute the attack

        try {
            attacker.attack(attackee);
        } catch (RefusalException e) {
            // popup that attacker is a pacifist
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
