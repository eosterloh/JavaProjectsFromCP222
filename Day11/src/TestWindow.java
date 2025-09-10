import javax.swing.*;
import java.awt.*;

public class TestWindow extends JFrame {
    public TestWindow() {
        super("TestWindow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel helloWidget = new JLabel("Hello, World!");
        this.add(helloWidget);
        JButton Btn = new JButton("Hello, World!");
        Btn.addActionListener(new HiButtonActionListener());
        this.add(Btn);
        this.setMinimumSize(new Dimension(800, 600));
    }
}
