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

class BoardEdge extends Board {
    BoardUser userBoard;
    BoardGroup groupBoard;
    ArrayList<DrawableEdge> edges = new ArrayList<>();

    BoardEdge(Bipartite b, BoardUser ub, BoardGroup gb) {
        super(300);
        userBoard = ub;
        groupBoard = gb;
        bipartite = b;
        for (Edge edge: bipartite.getEdges()){
            edges.add(new DrawableEdge(edge, Color.BLACK));
        }
    }

    public void setDefault(){
        for (Edge edge1: bipartite.getMaxMatching()){
            for (DrawableEdge edge2: edges){
                if (edge2.equals(edge1)){
                    edge2.color = Color.BLACK;
                }
            }
        }
    }

    public void setMaxMatching(){
        for (Edge edge1: bipartite.getMaxMatching()){
            for (DrawableEdge edge2: edges){
                if (edge2.equals(edge1)){
                    edge2.color = Color.RED;
                }
            }
        }
    }

    private void drawEdges(Graphics g) {
        for (DrawableEdge edge : edges) {
            int ind_user = findIndex(bipartite.getFirstSide(), edge.getFirstNode().getItemData().id);
            int ind_group = findIndex(bipartite.getSecondSide(), edge.getSecondNode().getItemData().id);
            g.setColor(edge.color);
            if(g.getColor() == Color.RED)
                System.out.println(edge.toString());
            for (int i = 0; i < 3; ++i)
                g.drawLine(ind_user * userBoard.dx + 25 + userBoard.offsetX+i, 0, ind_group * groupBoard.dx + 25 + groupBoard.offsetX+i, 300);
        }
        System.out.println(edges.size());
    }

    private int findIndex(ArrayList<GraphNode> lst, int id) {
        for (int i = 0; i < lst.size(); ++i) {
            if (lst.get(i).getItemData().id == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, super.width, 310);
        drawEdges(g);
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
