package gui;

import pony.Pony;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BattleWindow extends JFrame {

    private GridBagLayout gb;
    private GridBagConstraints gc;

    private PonySelector psa;
    private PonySelector psb;
    private FightButton fight;
    // Construct a new PonyBattle Window
    public BattleWindow() {
        super("Pony Battle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the widgets
        fight = new FightButton();
        psa  = new PonySelector();
        psb  = new PonySelector();

        // Add the FightActionListener instance to fight button
        FightActionListener fa = new FightActionListener(psa, psb);
        fight.addActionListener(fa);

        // Place the widgets
        gb = new GridBagLayout();
        gc = new GridBagConstraints();
        setLayout(gb);
        place(fight, 0,1,2,0);
        place(psa, 0,0,1,1);
        place(psb, 1,0,1,1);

        // Have the window size itself
        pack();
    }

    /** Changes which ponies can be selected
     *
     * @param ponies the ponies to choose from
     */
    public void setSelectablePonies(ArrayList<Pony> ponies) {
        psa.setPonies(ponies);
        psb.setPonies(ponies);
        // Have window size itself again?
        pack();
    }

    private void place(Component c, int x, int y, int spanx, int spany) {
        gc.gridx=x;
        gc.gridy=y;
        gc.gridwidth=spanx;
        gc.gridheight=spany;
        this.add(c);
        gb.setConstraints(c,gc);
    }

    // TODO: delete this method
    public static void main(String[] args) {
        BattleWindow w = new BattleWindow();
        w.setVisible(true);
    }
}
