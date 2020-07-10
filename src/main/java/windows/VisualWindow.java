package windows;

import algo.Bipartite;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import parser.ParserFacade;
import utils.Mediator;

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
    private final Mediator mediator;


    VisualWindow() throws InterruptedException, ClientException, ApiException, IOException {
        super();
        bip = new Bipartite(new ParserFacade().getMatchingDataList(Integer.valueOf(MainWindow.getInstance().getVkId())));
        userBoard = new BoardUser(bip);
        groupBoard = new BoardGroup(bip);
        edgeBoard = new BoardEdge(bip, userBoard,groupBoard);
        mediator = new Mediator(this);
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
            buttonPanel.maxMatching.setEnabled(false);
        });
        buttonPanel.erase.addActionListener((ActionEvent e) -> {
            edgeBoard.setDefault();
            edgeBoard.repaint();
            bip.resetMatching();
            mediator.reset();
            buttonPanel.step.setEnabled(true);
            buttonPanel.maxMatching.setEnabled(false);
        });
        buttonPanel.step.addActionListener((ActionEvent e) ->{
            bip.nextStep(mediator);
        });

        buttonPanel.maxMatching.setEnabled(false);
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public BoardEdge getEdgeBoard() {
        return edgeBoard;
    }

    public void setButtonActive(JButton btn) {
        btn.setEnabled(true);
    }

    public void setButtonInActive(JButton btn) {
        btn.setEnabled(false);
    }

}

