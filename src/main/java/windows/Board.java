package windows;

import algo.Bipartite;
import algo.Edge;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Custom abstract board for painting.
 */
abstract public class Board extends JPanel {
    protected int width = 950;
    Bipartite bipartite;

    Board(int h) {
        super();
        setBackground(Color.white);
        setSize(width, h);
    }
}

/**
 * Custom abstract board of nodes for painting.
 */
abstract class BoardNode extends Board {
    /**
     * Constructor.
     */
    BoardNode() {
        super(100);
    }

    protected int dx;
    protected int offsetY;
    protected int offsetX;
    protected BufferImagesUsers imagesUsers;
    protected BufferImagesGroups imagesGroups;
}

/**
 * Custom board of users for painting.
 */
class BoardUser extends BoardNode {
    /**
     * Constructor.
     * @param bip Bipartite graph.
     * @throws IOException -
     */
    BoardUser(Bipartite bip) throws IOException {
        super();
        dx = 160;
        offsetY = 30;
        offsetX = Math.max(0, (bip.getSecondSide().size() * dx - bip.getFirstSide().size() * dx) / 2 + (width - bip.getSecondSide().size() * dx) / 2);
        bipartite = bip;
        imagesUsers = new BufferImagesUsers(bip);
    }

    /**
     * Override method for correct painting.
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, super.width, 110);
        for (int i = 0; i < bipartite.getFirstSide().size(); ++i) {
            Image img = imagesUsers.listPhotos.get(i);;
            int odd = (i % 2) * 17;
            Font font = new Font(Font.DIALOG, Font.BOLD, 12);
            String name = bipartite.getFirstSide().get(i).getItemData().name;
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString(name, i * dx + offsetX - (g.getFontMetrics().stringWidth(name) - 50) / 2, offsetY + 75 - odd);
            g.drawImage(img, i * dx + offsetX, offsetY - odd, null);
        }
    }
}

/**
 * Custom board of groups for painting.
 */
class BoardGroup extends BoardNode {
    /**
     * Constructor.
     * @param bip Bipartite graph.
     * @throws IOException -
     */
    BoardGroup(Bipartite bip) throws IOException {
        super();
        dx = 60;
        offsetY = 0;
        offsetX = Math.max(0, (width - bip.getSecondSide().size() * dx) / 2);
        bipartite = bip;
        imagesGroups = new BufferImagesGroups(bip);
    }
    /**
     * Override method for correct painting.
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, super.width, 100);
        for (int i = 0; i < bipartite.getSecondSide().size(); ++i) {
            Image img;
            img = imagesGroups.listPhotos.get(i);
            g.drawImage(img, i * dx + offsetX, offsetY, null);
        }
    }
}

/**
 * Class for add to Edge class color field.
 */
class DrawableEdge extends Edge {
    Color color;

    /**
     * Constructor.
     * @param e edge without color
     * @param clr color
     */
    public DrawableEdge(Edge e, Color clr) {
        super(e.getFirstNode(), e.getSecondNode());
        color = clr;
    }

    /**
     * Equals method
     * @param obj other
     * @return True if equals.
     */
    public boolean equals(Edge obj) {
        return this.getFirstNode().equals(obj.getFirstNode()) && this.getSecondNode().equals(obj.getSecondNode());
    }
}
