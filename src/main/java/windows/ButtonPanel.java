package windows;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton toEnd = new JButton(">>");
    JButton toBegin = new JButton("<<");
    JButton stepForward = new JButton(">");
    JButton stepBack = new JButton("<");

    public JButton getStep() {
        return stepForward;
    }

    @Deprecated
    public JButton getMaxMatching() {
        return getStep();
    }


    public JButton getErase() {
        return toBegin;
    }

    ButtonPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        stepForward.setBorder(new RoundedBorder(10));
        stepBack.setBorder(new RoundedBorder(10));
        toEnd.setBorder(new RoundedBorder(10));
        toBegin.setBorder(new RoundedBorder(10));
        add(toBegin);
        add(Box.createHorizontalStrut(10));
        add(stepBack);
        add(Box.createHorizontalStrut(10));
        add(stepForward);
        add(Box.createHorizontalStrut(10));
        add(toEnd);
    }
}
