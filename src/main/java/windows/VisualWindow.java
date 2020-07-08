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
    private final BoardUser userBoard;
    private final BoardEdge edgeBoard = new BoardEdge();
    private final BoardGroup groupBoard = new BoardGroup();
    private final ButtonPanel buttonPanel = new ButtonPanel();
    private final Bipartite bip;
    private static VisualWindow instance;

    public static VisualWindow getInstance() throws InterruptedException, ClientException, ApiException, IOException {
        if (instance == null)
            instance = new VisualWindow();
        instance.setVisible(true);
        return instance;
    }

    private VisualWindow() throws IOException, InterruptedException, ClientException, ApiException {
        super();
        bip = new Bipartite(new ParserFacade().getMatchingDataList(Integer.valueOf(MainWindow.getInstance().getVkId())));
        userBoard = new BoardUser(bip.getFirstSide().size(), bip.getSecondSide().size());
        ImageIcon icon = new ImageIcon("resources/icon.png");
        setModal(true);
        setTitle("Visualization");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBackground(Color.lightGray);
        setCustomSize();
        setUserBoard();
        setEdgeBoard();
        setGroupBoard();
        setButtonPanel();
        setLayout(gbl);
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 400, tk.getScreenSize().height / 2 - 400, 800, 900);
        setResizable(false);
    }

    private void setUserBoard() {
        consLayout.anchor = GridBagConstraints.WEST;
        consLayout.fill = GridBagConstraints.VERTICAL;
        consLayout.gridheight = GridBagConstraints.REMAINDER;
        consLayout.gridwidth = 1;
        consLayout.gridx = 1;
        consLayout.gridy = 1;
        consLayout.insets = new Insets(0, 0, 0, 0);
        consLayout.ipadx = userBoard.getWidth();
        consLayout.ipady = userBoard.getHeight();
        gbl.setConstraints(userBoard, consLayout);
        add(userBoard);
    }

    private void setEdgeBoard() {
        consLayout.gridx = 2;
        consLayout.gridy = 1;
        consLayout.ipadx = edgeBoard.getWidth();
        consLayout.ipady = edgeBoard.getHeight();
        gbl.setConstraints(edgeBoard, consLayout);
        add(edgeBoard);
    }

    private void setGroupBoard() {
        consLayout.gridx = 3;
        consLayout.gridy = 1;
        consLayout.ipadx = groupBoard.getWidth();
        consLayout.ipady = groupBoard.getHeight();
        gbl.setConstraints(groupBoard, consLayout);
        add(groupBoard);
    }

    private void setButtonPanel() throws IOException {
        consLayout.gridx = 4;
        consLayout.gridy = 1;
        consLayout.insets = new Insets(0, 10, 0, 0);
        consLayout.ipadx = buttonPanel.getWidth();
        consLayout.ipady = buttonPanel.getHeight();
        gbl.setConstraints(buttonPanel, consLayout);
        add(buttonPanel);
        buttonPanel.draw.addActionListener((ActionEvent e) -> {
            try {
                this.drawBipartite();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        buttonPanel.maxMatching.addActionListener((ActionEvent e) -> {
            edgeBoard.showBipartite(bip);
        });
        buttonPanel.erase.addActionListener((ActionEvent e) -> {
            userBoard.erase();
            edgeBoard.erase();
            groupBoard.erase();
        });
    }

    private void drawBipartite() throws IOException {
        userBoard.setNodes(bip.getFirstSide());
        groupBoard.setNodes(bip.getSecondSide());
        edgeBoard.setEdges(bip);
    }
}

class ButtonPanel extends JPanel {
    JButton maxMatching = new JButton("Max Matching");
    JButton draw = new JButton("Draw");
    JButton erase = new JButton("Erase");

    ButtonPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        draw.setBorder(new RoundedBorder(10));
        maxMatching.setBorder(new RoundedBorder(10));
        erase.setBorder(new RoundedBorder(10));
        add(draw);
        add(Box.createHorizontalStrut(10));
        add(maxMatching);
        add(Box.createHorizontalStrut(10));
        add(erase);
    }