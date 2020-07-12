package windows;

import algo.Bipartite;
import algo.Edge;
import algo.GraphNode;

import java.awt.*;
import java.util.ArrayList;

/**
 * Custom class of edges for painting.
 */
public class BoardEdge extends Board {
    BoardUser userBoard;
    BoardGroup groupBoard;
    ArrayList<DrawableEdge> edges = new ArrayList<>();

    /**
     * Constructor.
     * @param b Bipartite graph
     * @param ub Object of BoardUser
     * @param gb Object of BoardGroup
     */
    BoardEdge(Bipartite b, BoardUser ub, BoardGroup gb) {
        super(300);
        userBoard = ub;
        groupBoard = gb;
        bipartite = b;
        for (Edge edge: bipartite.getEdges()){
            edges.add(new DrawableEdge(edge, Color.BLACK));
        }
    }

    /**
     * Method to paint all edges black.
     */
    public void setDefault(){
        for (Edge edge1: bipartite.getEdges()){
            for (DrawableEdge edge2: edges){
                if (edge2.equals(edge1)){
                    edge2.color = Color.BLACK;
                }
            }
        }
    }

    /**
     * Method to paint all edges in edgeList to color.
     * @param edgeList List of edges
     * @param color Color
     */
    public void setMaxMatching(ArrayList<Edge> edgeList, Color color) {
        setDefault();
        for (Edge edge: edgeList){
            drawEdge(edge, color);
        }
    }

    /**
     * Method to paint edge in color.
     * @param edge .
     * @param color .
     */
    public void drawEdge(Edge edge, Color color) {
        for (DrawableEdge drawableEdge: edges){
            if (drawableEdge.equals(edge)){
                drawableEdge.color = color;
            }
        }
    }

    /**
     * Method for output edges to board.
     * @param g
     */
    private void drawEdges(Graphics g) {
        for (DrawableEdge edge : edges) {
            int ind_user = findIndex(bipartite.getFirstSide(), edge.getFirstNode().getItemData().id);
            int ind_group = findIndex(bipartite.getSecondSide(), edge.getSecondNode().getItemData().id);
            g.setColor(edge.color);
            for (int i = 0; i < 3; ++i)
                g.drawLine(ind_user * userBoard.dx + 25 + userBoard.offsetX+i, 0, ind_group * groupBoard.dx + 25 + groupBoard.offsetX+i, 300);
        }
    }

    /**
     * Just linear search.
     * @param lst Haystack
     * @param id Needle
     * @return index first equal.
     */
    private int findIndex(ArrayList<GraphNode> lst, int id) {
        for (int i = 0; i < lst.size(); ++i) {
            if (lst.get(i).getItemData().id == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Override method for correct painting.
     * @param g graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, super.width, 310);
        drawEdges(g);
    }
}
