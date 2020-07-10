package windows;

import algo.Bipartite;
import algo.Edge;
import algo.GraphNode;

import java.awt.*;
import java.util.ArrayList;

public class BoardEdge extends Board {
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
        for (Edge edge1: bipartite.getEdges()){
            for (DrawableEdge edge2: edges){
                if (edge2.equals(edge1)){
                    edge2.color = Color.BLACK;
                }
            }
        }
    }

    public void setMaxMatching(){
        setMaxMatching(bipartite.getMaxMatching(), Color.RED);

    }

    public void setMaxMatching(ArrayList<Edge> edgeList, Color color) {
        for (Edge edge: edgeList){
            drawEdge(edge, color);
        }
    }

    public void drawEdge(Edge edge, Color color) {
        for (DrawableEdge drawableEdge: edges){
            if (drawableEdge.equals(edge)){
                drawableEdge.color = color;
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
