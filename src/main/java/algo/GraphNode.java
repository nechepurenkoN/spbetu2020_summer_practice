package algo;

import parser.ItemData;
import utils.Mediator;

import java.util.Objects;

public class GraphNode {
    private ItemData data;

    public GraphNode(ItemData data) {
        this.data = data;
    }

    public Boolean accept(Visitor visitor, Mediator mediator) {
        return visitor.visit(this, mediator);
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

