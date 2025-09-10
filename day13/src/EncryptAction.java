import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptAction implements ActionListener {
    MyWindow win;

    public EncryptAction(MyWindow w) {
        win = w;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // pull information from the input
        String input = win.getInput();

        // apply a rotation
        String output = encrypt(input);

        // write information to the output
        win.setOutput(output);
    }

    public String encrypt(String in) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<in.length(); i++) {
            // Check the range
            if(in.charAt(i)>='a' && in.charAt(i)<='z') {
                // Math is hard... Get the char, get it's location in the letters,
                //                 add 13 to that, then because the number might be too
                //                 big apply a mod, then add 'a' to get back to the ASCII
                //                 value
                char out = (char)((((in.charAt(i)-'a')+13)%26)+'a');
                sb.append(out);
            } else {
                // Do nothing if it's not a lower case letter
                sb.append(in.charAt(i));
            }
        }
        return sb.toString();
    }
}
