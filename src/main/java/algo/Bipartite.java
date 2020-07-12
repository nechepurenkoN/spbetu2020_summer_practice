package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import parser.MatchingData;
import utils.Mediator;

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

    public static ArrayList<Edge> getMatchingList(HashMap<GraphNode, GraphNode> matching) {
        ArrayList<Edge> result = new ArrayList<>();
        for (Map.Entry<GraphNode, GraphNode> currentEdge : matching.entrySet()) {
            result.add(new Edge(currentEdge.getValue(), currentEdge.getKey()));
        }
        return result;
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
        resultMatching = new HashMap<>();
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
            builder.append("\t\t").append(currentPair.getKey().toString());
            builder.append(" : ");
            for (SemiEdge currentSemiEdge : currentPair.getValue()) {
                builder.append(currentSemiEdge.getNode().toString()).append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }


    private void getMaxMatchingIteration(Mediator mediator) {
        int i = mediator.getCurrentStep();

        if (i == getFirstSide().size()) {
          mediator.end();
          return;
        }

        NodeVisitor nodeVisitor = new NodeVisitor(this, mediator);
        getIthNode(i).accept(nodeVisitor);

        mediator.stepForward();
    }

    private GraphNode getIthNode(int i) {
        assert i < getNodesCount();
        if (i < firstSide.size())
            return firstSide.get(i);
        return secondSide.get(i - firstSide.size());
    }

    private int getNodesCount() {
        return firstSide.size() + secondSide.size();
    }

    public HashMap<GraphNode, GraphNode> getMaxMatching() {
        return resultMatching;
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

    public void nextStep(Mediator mediator) {
        getMaxMatchingIteration(mediator);
    }

    public void prevStep(Mediator mediator) {
        mediator.stepBack();
    }

    public void setResultMatchingMap(HashMap<GraphNode, GraphNode> prevMatching) {
        resultMatching = prevMatching;
    }

    public void toBegin(Mediator mediator) {
        mediator.begin();
    }

    public void toEnd(Mediator mediator) {
        while (mediator.getCurrentStep() < getFirstSide().size()) {
            getMaxMatchingIteration(mediator);
        }
        getMaxMatchingIteration(mediator);
    }
}
