package algo;

public class Edge {
    private GraphNode firstNode;
    private GraphNode secondNode;

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
}
