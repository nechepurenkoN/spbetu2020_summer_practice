package windows;

import javax.swing.*;
import java.awt.*;

/**
 * Custom menu.
 */
public class MenuBar extends JMenuBar {
    /**
     * Constructor.
     */
    MenuBar(){
        super();
        add(Box.createHorizontalGlue());
        JButton info = new JButton();
        info.setBackground(Color.WHITE);
        info.setIcon(new ImageIcon("src\\main\\resources\\info.png"));
        info.setText("Info");
        info.addActionListener(e -> InfoWindow.getInstance());
        info.setBorder(BorderFactory.createEmptyBorder());
        add(info);
        add(Box.createHorizontalStrut(10));

    }
}
