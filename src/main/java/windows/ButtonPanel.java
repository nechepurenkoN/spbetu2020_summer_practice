package windows;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    CustomButton stepForward = new CustomButton("src\\main\\resources\\step_forward.png");
    CustomButton stepBack = new CustomButton("src\\main\\resources\\step_back.png");
    CustomButton toBegin = new CustomButton("src\\main\\resources\\to_begin.png");
    CustomButton toEnd = new CustomButton("src\\main\\resources\\to_end.png");

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
        // set layout and size
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        // add buttons to panel
        add(toBegin);
        add(Box.createHorizontalStrut(10));
        add(stepBack);
        add(Box.createHorizontalStrut(10));
        add(stepForward);
        add(Box.createHorizontalStrut(10));
        add(toEnd);
    }
}

class CustomButton extends JButton{
    CustomButton(String img_path){
        super();
        setBackground(Color.white);
        setIcon(new ImageIcon(img_path));
        setBorder(new RoundedBorder(1));
    }
}