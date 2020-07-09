package windows;

import algo.Bipartite;
import algo.Edge;
import algo.GraphNode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

abstract public class Board extends JPanel {
    protected int width = 950;

    Board(int h) {
        super();
        setSize(width, h);
        setBackground(Color.white);
    }

    public void erase() {
        paintComponent(getGraphics());
    }

    public static Image getImageFromURL(String url) throws IOException {
        if (url.equals("default")) {
            return null;
        }
        URL u = new URL(url);
        BufferedImage image = ImageIO.read(u);
        image = image.getSubimage(image.getWidth() / 2 - 25, image.getHeight() / 2 - 25, 50, 50);
        ImageIcon ret = new ImageIcon(image);
        return ret.getImage();
    }
}

abstract class BoardNode extends Board {
    BoardNode() {
        super(100);
    }

    protected int dx;
    protected int offsetY;
    protected int offsetX;

    public void setNodes(ArrayList<GraphNode> lst) throws IOException {
        for (int i = 0; i < lst.size(); ++i) {
            Image img = Board.getImageFromURL(lst.get(i).getItemData().photo);
            if (img != null) {
                Graphics g = getGraphics();
                g.drawImage(img, i * dx + offsetX, offsetY, null);
                if (this instanceof BoardUser){
                    Font font = new Font(Font.DIALOG, Font.BOLD, 12);
                    String name = lst.get(i).getItemData().name;
                    g.setFont(font);
                    g.drawString(name, i * dx + offsetX - (g.getFontMetrics().stringWidth(name) - 50)/2, offsetY + 63);
                }
            }
        }
    }
}

class BoardUser extends BoardNode {
    BoardUser(Bipartite bip) {
        super();
        dx = 110;
        offsetY = 20;
        offsetX = Math.max(0, (bip.getSecondSide().size() * dx - bip.getFirstSide().size() * dx) / 2 + (width - bip.getSecondSide().size() * dx) / 2);
    }
}

class BoardGroup extends BoardNode {
    BoardGroup(Bipartite bip) {
        super();
        dx = 60;
        offsetY = 0;
        offsetX = Math.max(0, (width - bip.getSecondSide().size() * dx) / 2);
    }
}

class BoardEdge extends Board {
    BoardEdge() {
        super(300);
    }

    public void setEdges(Bipartite bip, BoardUser userBoard, BoardGroup groupBoard) {
        drawEdges(bip, Color.BLACK, userBoard, groupBoard, bip.getEdges());
    }

    public void drawBipartite(Bipartite bip, BoardUser userBoard, BoardGroup groupBoard) {
        drawEdges(bip, Color.RED, userBoard, groupBoard, bip.getMaxMatching());
    }

    private void drawEdges(Bipartite bip, Color clr, BoardUser userBoard, BoardGroup groupBoard, ArrayList<Edge> edges) {
        for (Edge edge : edges) {
            int ind_user = findIndex(bip.getFirstSide(), edge.getFirstNode().getItemData().id);
            int ind_group = findIndex(bip.getSecondSide(), edge.getSecondNode().getItemData().id);
            Graphics g = getGraphics();
            g.setColor(clr);
            g.drawLine(ind_user * userBoard.dx + 25 + userBoard.offsetX, 0, ind_group * groupBoard.dx + 25 + groupBoard.offsetX, 300);
        }
    }

    private int findIndex(ArrayList<GraphNode> lst, int id) {
        for (int i = 0; i < lst.size(); ++i) {
            if (lst.get(i).getItemData().id == id) {
                return i;
            }
        }
        return -1;
    }
}
