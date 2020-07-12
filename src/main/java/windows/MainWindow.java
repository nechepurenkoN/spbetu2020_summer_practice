package windows;

import algo.Bipartite;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiParamException;
import com.vk.api.sdk.exceptions.ClientException;
import parser.ParserFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MainWindow extends JFrame {
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints consLayout = new GridBagConstraints();
    private final JLabel logo = new JLabel(new ImageIcon("src\\main\\resources\\logo.png"));
    private final JLabel greeting = new JLabel("<html><h1 align=center>VK Bipartite</h1><p align=center>" +
            "Данное решение позволяет визуализировать алгоритм поиска " +
            "максимального паросочетания в двудольном графе. Доли графа " +
            "представляют собой группу пользователей социальной сети ВКонтакте " +
            "и группу сообществ, на которые они подписаны.</p></html>");
    private final InputPanel inputPanel = new InputPanel();
    private final JButton startButton = new JButton("Start!");
    private static MainWindow instance;

    public static MainWindow getInstance() {
        if (instance == null)
            instance = new MainWindow();
        return instance;
    }

    private MainWindow() {
        super("Vk Bipartite");
        ImageIcon icon = new ImageIcon("src\\main\\resources\\icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setCustomSize();
        setLayout(gbl);
        setGreeting();
        setInputPanel();
        setButtonStart();
        setMenu();
        setBackground(Color.white);
        setVisible(true);
    }

    static class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MainWindow.getInstance().startVisualisation();
            } catch (InterruptedException | ClientException | ApiException | IOException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 300, tk.getScreenSize().height / 2 - 220, 600, 440);
        setResizable(false);
    }

    private void setGreeting() {
        greeting.setHorizontalAlignment(JLabel.CENTER);
        consLayout.anchor = GridBagConstraints.NORTH;
        consLayout.fill = GridBagConstraints.HORIZONTAL;
        consLayout.gridheight = 1;
        consLayout.gridwidth = GridBagConstraints.REMAINDER;
        consLayout.gridx = 1;
        consLayout.gridy = 1;
        consLayout.ipady = logo.getHeight();
        consLayout.insets = new Insets(10, 0, 0, 0);
        gbl.setConstraints(logo, consLayout);
        consLayout.gridy = GridBagConstraints.RELATIVE;
        consLayout.ipady = greeting.getHeight();
        consLayout.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(greeting, consLayout);
        add(logo);
        add(greeting);
    }

    private void setInputPanel() {
        consLayout.gridy = GridBagConstraints.RELATIVE;
        consLayout.gridx = 1;
        consLayout.ipadx = 300;
        consLayout.fill = GridBagConstraints.NONE;
        consLayout.ipady = 10;
        consLayout.insets = new Insets(10, 0, 0, 0);
        gbl.setConstraints(inputPanel, consLayout);
        add(inputPanel);
    }

    private void setButtonStart() {
        startButton.setBackground(Color.white);
        startButton.setBorder(new RoundedBorder(10));
        startButton.addActionListener(new StartActionListener());
        consLayout.gridwidth = GridBagConstraints.NONE;
        consLayout.gridx = 1;
        consLayout.gridy = GridBagConstraints.RELATIVE;
        consLayout.ipadx = 30;
        consLayout.ipady = 10;
        consLayout.fill = GridBagConstraints.NONE;
        consLayout.insets = new Insets(20, 0, 0, 0);
        gbl.setConstraints(startButton, consLayout);
        add(startButton);
    }

    public void startVisualisation() throws InterruptedException, ClientException, ApiException, IOException {
        try {
            Bipartite bip = new Bipartite(new ParserFacade().getMatchingDataList(Integer.valueOf(inputPanel.getText())));
            new VisualWindow(bip);
        } catch (RuntimeException | ApiParamException e) {
            new ErrorWindow(e.getMessage());
        }
    }

    private void setMenu(){
        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);
//        Authors authors = new Authors();
//        consLayout.anchor=GridBagConstraints.SOUTH;
//        consLayout.fill = GridBagConstraints.HORIZONTAL;
//        consLayout.gridheight = 1;
//        consLayout.gridwidth = GridBagConstraints.REMAINDER;
//        consLayout.gridx = 1;
//        consLayout.gridy = GridBagConstraints.RELATIVE;
//        consLayout.ipadx = authors.getWidth();
//        consLayout.ipady = authors.getHeight();
//        gbl.setConstraints(authors, consLayout);
//        add(authors);
    }

}



class InputPanel extends JPanel {
    private final JTextField inputLine = new JTextField();

    InputPanel() {
        super();
        setPreferredSize(new Dimension(390, 10));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        JLabel inputLabel = new JLabel("VK ID:");
        inputLabel.setHorizontalTextPosition(JLabel.RIGHT);
        inputLine.addKeyListener(new StartKeyListener());
        add(inputLabel);
        inputLine.setPreferredSize(new Dimension(300, 10));
        add(Box.createHorizontalStrut(10));
        add(inputLine);
    }

    static class StartKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    MainWindow.getInstance().startVisualisation();
                } catch (InterruptedException | ClientException | ApiException | IOException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public String getText() {
        if (inputLine.getText().length() != 0) {
            return inputLine.getText();
        }
        return "147946476";
    }
}

