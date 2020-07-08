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
    Board(int w) {
        super();
        setSize(w, 900);
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

    protected int dy;
    protected int offsetX;
    protected int offsetY;

    public void setNodes(ArrayList<GraphNode> lst) throws IOException {
        for (int i = 0; i < lst.size(); ++i) {
            Image img = Board.getImageFromURL(lst.get(i).getItemData().photo);
            if (img != null) {
                Graphics g = getGraphics();
                g.drawImage(img, offsetX, i * dy, null);
            }
        }
    }
}

class BoardUser extends BoardNode {
    BoardUser(int userCount, int groupCount) {
        super();
        dy = 110;
        offsetX = 50;
//        offsetY = Math.max(0, (groupCount-userCount)/2 * 60);
    }
}

class BoardGroup extends BoardNode {
    private final int r = 50;

    BoardGroup() {
        super();
        dy = 60;
        offsetX = 0;
        offsetY = 0;
    }
}

class BoardEdge extends Board {
    private final int width = 300;

    BoardEdge() {
        super(300);
    }

    public void setEdges(Bipartite bip) {
        drawEdges(bip.getEdges(), bip.getFirstSide(), bip.getSecondSide(), Color.BLACK);
    }

    public void showBipartite(Bipartite bip) {
        drawEdges(bip.getMaxMatching(), bip.getFirstSide(), bip.getSecondSide(), Color.RED);
    }

    private void drawEdges(ArrayList<Edge> edges, ArrayList<GraphNode> users, ArrayList<GraphNode> groups, Color clr) {
        for (int i = 0; i < edges.size(); ++i) {
            int ind_user = findIndex(users, edges.get(i).getFirstNode().getItemData().id);
            int ind_group = findIndex(groups, edges.get(i).getSecondNode().getItemData().id);
            Graphics g = getGraphics();
            g.setColor(clr);
            g.drawLine(0, ind_user * 110 + 25, width, ind_group * 60 + 25);
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