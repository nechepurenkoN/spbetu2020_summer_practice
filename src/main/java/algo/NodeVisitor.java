package algo;

import utils.Mediator;

import java.util.HashSet;

interface Visitor {
    Boolean visit(GraphNode node);
}

/** Class for graph dfs traversing with Kunh's algorithm support
 * @author nechepurenkon
 */
public class NodeVisitor implements Visitor {
    private final HashSet<GraphNode> visited;
    private final Bipartite graph;
    private final Mediator mediator;

    public NodeVisitor(Bipartite graph, Mediator mediator) {
        visited = new HashSet<>();
        this.graph = graph;
        this.mediator = mediator;
    }

    /** Perform action with node while traversing
     *
     * @param node - current node
     * @return boolean result if better path exist
     */
    public Boolean visit(GraphNode node) {
        if (visited.contains(node))
            return Boolean.FALSE;
        visited.add(node);
        for (SemiEdge currentSemiEdge : graph.getAdjacentList(node)) {
            GraphNode adjacentNode = currentSemiEdge.getNode();
            if (graph.getResultMatching(adjacentNode) == null || graph.getResultMatching(adjacentNode).accept(this)) {
                // if vertex is not connected or better path exists
                graph.setResultMatching(adjacentNode, node);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
