package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InfoWindow extends JFrame {
    private static InfoWindow instance;

    public static InfoWindow getInstance() {
        if (instance == null)
            instance = new InfoWindow();
        if(! instance.isVisible()){
            instance.setVisible(true);
        }
        return instance;
    }

    private InfoWindow() {
        setIconImage(new ImageIcon("src\\main\\resources\\icon.png").getImage());
        setTitle("Info");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(tk.getScreenSize().width / 2 - 150, tk.getScreenSize().height / 2 - 150, 300, 300);
        add(new JLabel("<html><h4>Ссылки:</h4></html>"));
        add(new Authors());
        setResizable(false);
        setVisible(true);
    }
}

class Authors extends JPanel {
    JLabel nn = new JLabel("Нечепуренко Никита");
    JLabel ta = new JLabel("Терехов Александр");
    JLabel tt = new JLabel("Торосян Тимофей");
    JLabel repo = new JLabel("GitHub");
    JLabel vk = new JLabel("Вконтакте");

    Authors() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        goWebsite(nn, "https://github.com/nechepurenkoN", nn.getText());
        goWebsite(ta, "https://github.com/snchz29", ta.getText());
        goWebsite(tt, "https://github.com/sandman595", tt.getText());
        goWebsite(repo, "https://github.com/nechepurenkoN/spbetu2020_summer_practice", repo.getText());
        goWebsite(vk, "https://vk.com", vk.getText());
        add(nn);
        add(ta);
        add(tt);
        add(repo);
        add(vk);
    }

    private void goWebsite(JLabel website, final String url, String text) {
        website.setText("<html><a href=\"\">" + text + "</a></html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (URISyntaxException | IOException ignored) {
                }
            }
        });
    }
}