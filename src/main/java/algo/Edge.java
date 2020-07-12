package algo;

import java.util.Objects;

/** Edge class to store pair of Vertexes
 * @author nechepurenkon
 */
public class Edge {
    private final GraphNode firstNode;
    private final GraphNode secondNode;

    public Edge(GraphNode firstNode, GraphNode secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    public GraphNode getFirstNode() {
        return firstNode;
    }

    public GraphNode getSecondNode(){
        return secondNode;
    }

    public String toString() {
        return "(" + firstNode.toString() + ";" + secondNode.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(firstNode, edge.firstNode) &&
                Objects.equals(secondNode, edge.secondNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNode, secondNode);
    }
}
