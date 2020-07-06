package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualWindow extends JFrame {
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints consLayout = new GridBagConstraints();
    private final BoardNode userBoard = new BoardNode();
    private final BoardEdge edgeBoard = new BoardEdge();
    private final BoardNode groupBoard = new BoardNode();
    private final ButtonPanel buttonPanel = new ButtonPanel();

    VisualWindow() {
        super("Visualization");
        ImageIcon icon = new ImageIcon("res/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBackground(Color.lightGray);
        setCustomSize();
        setUserBoard();
        setEdgeBoard();
        setGroupBoard();
        setButtonPanel();
        setLayout(gbl);
        setVisible(true);
        // TODO: check id
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 350, tk.getScreenSize().height / 2 - 300, 700, 600);
        setResizable(false);
    }

    private void setUserBoard() {
        consLayout.anchor = GridBagConstraints.WEST;
        consLayout.fill = GridBagConstraints.VERTICAL;
        consLayout.gridheight = GridBagConstraints.REMAINDER;
        consLayout.gridwidth = 1;
        consLayout.gridx = 1;
        consLayout.gridy = 1;
        consLayout.ipadx = userBoard.getWidth();
        consLayout.ipady = userBoard.getHeight();
        gbl.setConstraints(userBoard, consLayout);
        add(userBoard);
        userBoard.setBackground(Color.BLUE);
    }

    private void setEdgeBoard() {
        consLayout.gridx = 2;
        consLayout.gridy = 1;
        consLayout.ipadx = edgeBoard.getWidth();
        consLayout.ipady = edgeBoard.getHeight();
        gbl.setConstraints(edgeBoard, consLayout);
        add(edgeBoard);
        edgeBoard.setBackground(Color.RED);
    }

    private void setGroupBoard() {
        consLayout.gridx = 3;
        consLayout.gridy = 1;
        consLayout.ipadx = groupBoard.getWidth();
        consLayout.ipady = groupBoard.getHeight();
        gbl.setConstraints(groupBoard, consLayout);
        add(groupBoard);
        groupBoard.setBackground(Color.GREEN);
    }

    private void setButtonPanel() {
        consLayout.anchor = GridBagConstraints.SOUTH;
        consLayout.gridx = 4;
        consLayout.ipady = 1;
        consLayout.ipadx = buttonPanel.getWidth();
        consLayout.ipady = buttonPanel.getHeight();
        gbl.setConstraints(buttonPanel, consLayout);
        add(buttonPanel);
    }
}

class ButtonPanel extends JPanel{
    JButton step = new JButton("Step");
    JButton play = new JButton("Play");

    ButtonPanel() {
        super();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        step.setBorder(new RoundedBorder(10));
        play.setBorder(new RoundedBorder(10));
        step.addActionListener(new StepActionListener());
        play.addActionListener(new PlayActionListener());
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(step, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagLayout.setConstraints(step, gridBagConstraints);
        add(step);
        add(play);
        setLayout(gridBagLayout);
    }

    static class StepActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Step pressed");
        }
    }

    static class PlayActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Play pressed");
        }
    }
}