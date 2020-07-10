package windows;

import algo.Bipartite;
import algo.Edge;
import algo.GraphNode;
import parser.ItemData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract public class Board extends JPanel {
    protected int width = 950;
    Bipartite bipartite;

    Board(int h) {
        super();
        setBackground(Color.white);
        setSize(width, h);
    }

    public void erase() {
        paintComponent(getGraphics());
    }
}

abstract class BoardNode extends Board {
    BoardNode() {
        super(100);
    }

    protected int dx;
    protected int offsetY;
    protected int offsetX;
    protected BufferImagesUsers imagesUsers;
    protected BufferImagesGroups imagesGroups;

    public void setNodes(ArrayList<GraphNode> lst, Graphics g) {
        for (int i = 0; i < lst.size(); ++i) {
            Image img;
            if (this instanceof BoardUser) {
                img = imagesUsers.listPhotos.get(i);
                Font font = new Font(Font.DIALOG, Font.BOLD, 12);
                String name = lst.get(i).getItemData().name;
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString(name, i * dx + offsetX - (g.getFontMetrics().stringWidth(name) - 50) / 2, offsetY + 63);
            } else
                img = imagesGroups.listPhotos.get(i);
            g.drawImage(img, i * dx + offsetX, offsetY, null);
        }
    }
}

class BoardUser extends BoardNode {
    BoardUser(Bipartite bip) throws IOException {
        super();
        dx = 110;
        offsetY = 20;
        offsetX = Math.max(0, (bip.getSecondSide().size() * dx - bip.getFirstSide().size() * dx) / 2 + (width - bip.getSecondSide().size() * dx) / 2);
        bipartite = bip;
        imagesUsers = new BufferImagesUsers(bip);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, super.width, 110);
        setNodes(bipartite.getFirstSide(), g);
    }
}

class BoardGroup extends BoardNode {
    BoardGroup(Bipartite bip) throws IOException {
        super();
        dx = 60;
        offsetY = 0;
        offsetX = Math.max(0, (width - bip.getSecondSide().size() * dx) / 2);
        bipartite = bip;
        imagesGroups = new BufferImagesGroups(bip);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, super.width, 100);
        setNodes(bipartite.getSecondSide(), g);
    }
}

class DrawableEdge extends Edge{
    Color color;
    public DrawableEdge(Edge e, Color clr) {
        super(e.getFirstNode(), e.getSecondNode());
        color = clr;
    }

    public boolean equals(Edge obj) {
        return this.getFirstNode().getItemData().id == obj.getFirstNode().getItemData().id && this.getSecondNode().getItemData().id == obj.getSecondNode().getItemData().id;
    }
}
