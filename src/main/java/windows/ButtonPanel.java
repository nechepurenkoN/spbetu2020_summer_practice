package windows;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton draw = new JButton("Draw");
    JButton step = new JButton("Step");
    JButton maxMatching = new JButton("Max Matching");

    public JButton getDraw() {
        return draw;
    }

    public JButton getStep() {
        return step;
    }

    public JButton getMaxMatching() {
        return maxMatching;
    }

    public JButton getErase() {
        return erase;
    }

    JButton erase = new JButton("Erase");

    ButtonPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        draw.setBorder(new RoundedBorder(10));
        step.setBorder(new RoundedBorder(10));
        maxMatching.setBorder(new RoundedBorder(10));
        erase.setBorder(new RoundedBorder(10));
        add(draw);
        add(Box.createHorizontalStrut(10));
        add(step);
        add(Box.createHorizontalStrut(10));
        add(maxMatching);
        add(Box.createHorizontalStrut(10));
        add(erase);
    }
}
