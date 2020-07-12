package parser;

import algo.GraphNode;
import algo.SemiEdge;

import java.util.ArrayList;
import java.util.Collections;

/** Class that prepares response data to build graph
 * @author nechepurenkon
 */
public class MatchingData {
    private final ItemData self;
    private final ArrayList<ItemData> dataList;

    public MatchingData(ItemData self, ItemData[] dataList) {
        this.self = self;
        this.dataList = new ArrayList<>();
        Collections.addAll(this.dataList, dataList);
    }

    public GraphNode getKeyNode() {
        return new GraphNode(self);
    }

    public ArrayList<SemiEdge> getAdjacentList(){
        ArrayList<SemiEdge> adjacentList = new ArrayList<>();
        for (ItemData currentItemData : dataList) {
            adjacentList.add(new SemiEdge(new GraphNode(currentItemData)));
        }
        return adjacentList;
    }

    public String toString() {
        return "{" + self.toString() + " " + dataList.toString() + "}";
    }

}

