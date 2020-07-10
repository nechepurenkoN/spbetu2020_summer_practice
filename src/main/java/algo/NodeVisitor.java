package algo;

import utils.Mediator;

import java.util.HashSet;

interface Visitor {
    public abstract Boolean visit(GraphNode node, Mediator mediator);
}
public class NodeVisitor implements Visitor {
    private HashSet<GraphNode> visited;
    private Bipartite graph;

    public NodeVisitor(Bipartite graph) {
        visited = new HashSet<>();
        this.graph = graph;
    }

    public Boolean visit(GraphNode node, Mediator mediator) {
        if (visited.contains(node))
            return Boolean.FALSE;
        visited.add(node);
        for (SemiEdge currentSemiEdge : graph.getAdjacentList(node)) {
            GraphNode adjacentNode = currentSemiEdge.getNode();
            if (graph.getResultMatching(adjacentNode) == null || graph.getResultMatching(adjacentNode).accept(this, mediator)) {
                graph.setResultMatching(adjacentNode, node);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
