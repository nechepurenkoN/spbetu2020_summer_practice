package algo;

import parser.ItemData;

import java.util.Objects;

/** Class to store graph Vertex, with ability to visit
 * @author nechepurenkon
 */
public class GraphNode {
    private final ItemData data;

    public GraphNode(ItemData data) {
        this.data = data;
    }

    public Boolean accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String toString() {
        return data.toString();
    }

    public ItemData getItemData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return data.equals(graphNode.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}

