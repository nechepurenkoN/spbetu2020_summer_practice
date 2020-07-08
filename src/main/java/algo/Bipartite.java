package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import parser.MatchingData;

public class Bipartite {
    /**
     *
     *
     */
    private HashMap<GraphNode, ArrayList<SemiEdge>> listOfEdges;
    private ArrayList<GraphNode> firstSide;
    private ArrayList<GraphNode> secondSide;

    private HashMap<GraphNode, GraphNode> resultMatching;

    public Bipartite(ArrayList<MatchingData> data) {
        listOfEdges = new HashMap<>();
        resultMatching = new HashMap<>();
        initializeAll(data);
    }

    private void initializeAll(ArrayList<MatchingData> data) {
        initializeSides(data);
    }



    private void initializeSides(ArrayList<MatchingData> data) {
        HashSet<GraphNode> firstSideTemporarySet = new HashSet<>();
        HashSet<GraphNode> secondSideTemporarySet = new HashSet<>();
        for (MatchingData dataRow : data) {
            GraphNode keyNode = dataRow.getKeyNode();
            ArrayList<SemiEdge> adjacentList = dataRow.getAdjacentList();
            firstSideTemporarySet.add(keyNode);
            for (SemiEdge semiEdge : adjacentList) {
                secondSideTemporarySet.add(semiEdge.getNode());
            }
            listOfEdges.put(keyNode, adjacentList);
        }
        firstSide = new ArrayList<>(firstSideTemporarySet);
        secondSide = new ArrayList<>(secondSideTemporarySet);
    }


    public void setResultMatching(GraphNode lhs, GraphNode rhs) {
        resultMatching.put(lhs, rhs);
    }

    public GraphNode getResultMatching(GraphNode node) {
        if (resultMatching.containsKey(node))
            return resultMatching.get(node);
        return null;
    }

    public ArrayList<SemiEdge> getAdjacentList(GraphNode node) {
        if (!listOfEdges.containsKey(node))
            return new ArrayList<>();
        return listOfEdges.get(node);

    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bipartite:\n");
        builder.append("\tGraph:\n");
        builder.append(graphToString(listOfEdges));

        return builder.toString();
    }

    private String graphToString(HashMap<GraphNode, ArrayList<SemiEdge>> graph) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<GraphNode, ArrayList<SemiEdge>> currentPair : graph.entrySet()) {
            builder.append("\t\t" + currentPair.getKey().toString());
            builder.append(" : ");
            for (SemiEdge currentSemiEdge : currentPair.getValue()) {
                builder.append(currentSemiEdge.getNode().toString() + " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private void prepareParents() {
        resultMatching = new HashMap<>();
        for (int i = 0; i < getNodesCount(); i++) {
            NodeVisitor nodeVisitor = new NodeVisitor(this);
            getIthNode(i).accept(nodeVisitor);
        }

    }

    private GraphNode getIthNode(int i) {
        if (i < firstSide.size())
            return firstSide.get(i);
        return secondSide.get(i - firstSide.size());
    }

    private int getNodesCount() {
        return firstSide.size() + secondSide.size();
    }

    public ArrayList<Edge> getMaxMatching() {
        prepareParents();
        ArrayList<Edge> result = new ArrayList<>();
        for (Map.Entry<GraphNode, GraphNode> currentEdge : resultMatching.entrySet()) {
            if (currentEdge.getValue() != null)
                result.add(new Edge(currentEdge.getValue(), currentEdge.getKey()));
        }
        return result;
    }

    public ArrayList<GraphNode> getFirstSide() {
        return firstSide;
    }

    public ArrayList<GraphNode> getSecondSide() {
        return secondSide;
    }

    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> edgesList = new ArrayList<>();
        for (Map.Entry<GraphNode, ArrayList<SemiEdge>> nodeEdgesList : listOfEdges.entrySet()) {
            for (SemiEdge semiEdge : nodeEdgesList.getValue()) {
                edgesList.add(new Edge(nodeEdgesList.getKey(), semiEdge.getNode()));
            }
        }
        return edgesList;
    }

}
