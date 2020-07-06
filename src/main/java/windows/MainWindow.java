package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints consLayout = new GridBagConstraints();
    private final JLabel greeting = new JLabel("<html><p align=center>VK Bipartite<br>v.0.1</p></html>");
    private final JPanel inputPanel = new InputPanel();
    private final JButton startButton = new JButton("Start!");

    public MainWindow() {
        super("Vk Bipartite");
        ImageIcon icon = new ImageIcon("res/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setCustomSize();
        setLayout(gbl);
        setGreeting();
        setInputPanel();
        setButtonStart();
        setVisible(true);
        setBackground(Color.white);
    }

    static class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new VisualWindow();
        }
    }

    private void setCustomSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 250, tk.getScreenSize().height / 2 - 200, 500, 400);
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
        consLayout.ipadx = 490;
        consLayout.ipady = 190;
        gbl.setConstraints(greeting, consLayout);
        add(greeting);
    }

    private void setInputPanel() {
        consLayout.gridy = GridBagConstraints.RELATIVE;
        consLayout.gridx = 1;
        consLayout.ipadx = 300;
        consLayout.fill = GridBagConstraints.NONE;
        consLayout.ipady = 10;
        gbl.setConstraints(inputPanel, consLayout);
        add(inputPanel);
    }

    private void setButtonStart() {
        startButton.setBackground(Color.white);
        startButton.setBorder(new RoundedBorder(10));
        startButton.setPreferredSize(new Dimension(30, 7));
        startButton.addActionListener(new StartActionListener());
        consLayout.gridwidth = GridBagConstraints.NONE;
        consLayout.gridx = 1;
        consLayout.gridy = GridBagConstraints.RELATIVE;
        consLayout.ipadx = 30;
        consLayout.ipady = 7;
        consLayout.fill = GridBagConstraints.NONE;
        consLayout.insets = new Insets(20, 0, 0, 0);
        gbl.setConstraints(startButton, consLayout);
        add(startButton);
    }
}

class InputPanel extends JPanel {
    private final JLabel inputLabel = new JLabel("VK ID:");
    private final JTextField inputLine = new JTextField();

    InputPanel() {
        super();
        setSize(390, 10);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50, 10));
        inputLabel.setHorizontalTextPosition(JLabel.RIGHT);
        add(inputLabel);
        inputLine.setPreferredSize(new Dimension(300, 10));
        add(inputLine);
    }
}

