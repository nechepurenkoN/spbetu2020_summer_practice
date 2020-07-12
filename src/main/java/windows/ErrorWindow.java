package windows;

import javax.swing.*;

/**
 * Window which will create when error.
 */
public class ErrorWindow extends JFrame {
    /**
     * Constructor.
     * @param message Message in window.
     */
    ErrorWindow(String message){
        JOptionPane.showMessageDialog(new JFrame(), message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
