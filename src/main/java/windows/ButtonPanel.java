package windows;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton toEnd = new JButton(">>");
    JButton toBegin = new JButton("<<");
    JButton stepForward = new JButton(">");
    JButton stepBack = new JButton("<");

    public JButton getToEnd() {
        return toEnd;
    }

    public JButton getToBegin() {
        return toBegin;
    }

    public JButton getStepForward() {
        return stepForward;
    }

    public JButton getStepBack() {
        return stepBack;
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