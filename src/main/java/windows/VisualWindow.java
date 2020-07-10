package windows;

import algo.Bipartite;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import parser.ParserFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class VisualWindow extends JDialog {
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints consLayout = new GridBagConstraints();
    private final ButtonPanel buttonPanel = new ButtonPanel();
    private final BoardUser userBoard;
    private final BoardGroup groupBoard;
    private final BoardEdge edgeBoard;
    private final Bipartite bip;


    VisualWindow() throws InterruptedException, ClientException, ApiException, IOException {
        super();
        bip = new Bipartite(new ParserFacade().getMatchingDataList(Integer.valueOf(MainWindow.getInstance().getVkId())));
        userBoard = new BoardUser(bip);
        groupBoard = new BoardGroup(bip);
        edgeBoard = new BoardEdge(bip, userBoard,groupBoard);
        ImageIcon icon = new ImageIcon("resources/icon.png");
        setModal(true);
        setTitle("Visualization");
        setIconImage(icon.getImage());
        setCustomSize();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBackground(Color.lightGray);
        setUserBoard();
        setEdgeBoard();
        setGroupBoard();
        setButtonPanel();
        setLayout(gbl);
        setVisible(true);
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 475, tk.getScreenSize().height / 2 - 300, 950, 700);
        setResizable(false);
    }

    private void setUserBoard() {
        consLayout.anchor = GridBagConstraints.NORTH;
        consLayout.fill = GridBagConstraints.HORIZONTAL;
        consLayout.gridheight = 1;
        consLayout.gridwidth = GridBagConstraints.REMAINDER;
        consLayout.gridx = 1;
        consLayout.gridy = 1;
        consLayout.insets = new Insets(0, 0, 0, 0);
        consLayout.ipadx = userBoard.getWidth();
        consLayout.ipady = userBoard.getHeight();
        gbl.setConstraints(userBoard, consLayout);
        add(userBoard);
    }

    private void setEdgeBoard() {
        consLayout.gridx = 1;
        consLayout.gridy = 2;
        consLayout.ipadx = edgeBoard.getWidth();
        consLayout.ipady = edgeBoard.getHeight();
        gbl.setConstraints(edgeBoard, consLayout);
        add(edgeBoard);
    }

    private void setGroupBoard() {
        consLayout.gridx = 1;
        consLayout.gridy = 3;
        consLayout.ipadx = groupBoard.getWidth();
        consLayout.ipady = groupBoard.getHeight();
        gbl.setConstraints(groupBoard, consLayout);
        add(groupBoard);
    }

    private void setButtonPanel(){
        consLayout.gridwidth = GridBagConstraints.NONE;
        consLayout.fill = GridBagConstraints.NONE;
        consLayout.gridx = 1;
        consLayout.gridy = 4;
        consLayout.insets = new Insets(10, 0, 0, 0);
        consLayout.ipadx = buttonPanel.getWidth();
        consLayout.ipady = buttonPanel.getHeight();
        gbl.setConstraints(buttonPanel, consLayout);
        add(buttonPanel);
        buttonPanel.draw.addActionListener((ActionEvent e) -> {
        });
        buttonPanel.maxMatching.addActionListener((ActionEvent e) -> {
            edgeBoard.setMaxMatching();
            edgeBoard.repaint();
        });
        buttonPanel.erase.addActionListener((ActionEvent e) -> {
            edgeBoard.setDefault();
            edgeBoard.repaint();
        });
        buttonPanel.step.addActionListener((ActionEvent e) ->{});
    }
}

class ButtonPanel extends JPanel {
    JButton draw = new JButton("Draw");
    JButton step = new JButton("Step");
    JButton maxMatching = new JButton("Max Matching");
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