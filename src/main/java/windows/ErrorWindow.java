package windows;

import javax.swing.*;

public class ErrorWindow extends JFrame {
    ErrorWindow(String message){
        JOptionPane.showMessageDialog(new JFrame(), message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
