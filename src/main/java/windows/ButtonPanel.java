package windows;

import javax.swing.*;
import java.awt.*;

/**
 * Buttons for control visual part.
 */
public class ButtonPanel extends JPanel {
    CustomButton stepForward = new CustomButton("src\\main\\resources\\step_forward.png");
    CustomButton stepBack = new CustomButton("src\\main\\resources\\step_back.png");
    CustomButton toBegin = new CustomButton("src\\main\\resources\\to_begin.png");
    CustomButton toEnd = new CustomButton("src\\main\\resources\\to_end.png");

    /**
     * Getter.
     * @return button to end
     */
    public JButton getToEnd() {
        return toEnd;
    }

    /**
     * Getter.
     * @return button to begin
     */
    public JButton getToBegin() {
        return toBegin;
    }

    /**
     * Getter.
     * @return button step forward
     */
    public JButton getStepForward() {
        return stepForward;
    }

    /**
     * Getter.
     * @return button step back
     */
    public JButton getStepBack() {
        return stepBack;
    }

    /**
     * Constructor.
     */
    ButtonPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        add(toBegin);
        add(Box.createHorizontalStrut(10));
        add(stepBack);
        add(Box.createHorizontalStrut(10));
        add(stepForward);
        add(Box.createHorizontalStrut(10));
        add(toEnd);
    }
}

/**
 * Custom button with icon and without border.
 */
class CustomButton extends JButton{
    /**
     * Constructor.
     * @param img_path path to image.
     */
    CustomButton(String img_path){
        super();
        setBackground(Color.white);
        setIcon(new ImageIcon(img_path));
        setBorder(BorderFactory.createEmptyBorder());
    }
}