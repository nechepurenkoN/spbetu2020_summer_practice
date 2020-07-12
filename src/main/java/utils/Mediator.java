package utils;

import algo.Bipartite;
import algo.GraphNode;
import windows.VisualWindow;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/** Mediator between graph and VisualWindow
 * @author nechepurenkon
 */
public class Mediator {
    VisualWindow window;
    Bipartite graph;
    Integer loopStep;
    Stack<HashMap<GraphNode, GraphNode>> matchingStateStack;

    public Mediator(VisualWindow window, Bipartite graph) {
        this.window = window;
        this.graph = graph;
        loopStep = 0;
        matchingStateStack = new Stack<>();
    }

    public void reset() {
        loopStep = 0;
        matchingStateStack.clear();
        graph.setResultMatchingMap(new HashMap<>());
    }

    public void inc() {
        loopStep++;
    }

    public void dec() { loopStep--; }

    public int getCurrentStep() {
        return loopStep;
    }

    public void end() {
        window.getButtonPanel().getStepForward().setEnabled(false);
        window.getButtonPanel().getToEnd().setEnabled(false);
        window.getButtonPanel().getToBegin().setEnabled(true);
        window.getButtonPanel().getStepBack().setEnabled(true);
        passCurrentMatching(graph.getMaxMatching(), Color.RED);
    }

    public void begin() {
        reset();
        window.getButtonPanel().getStepBack().setEnabled(false);
        window.getButtonPanel().getToBegin().setEnabled(false);
        window.getButtonPanel().getStepForward().setEnabled(true);
        window.getButtonPanel().getToEnd().setEnabled(true);
        passCurrentMatching(graph.getMaxMatching(), Color.YELLOW);
    }

    /** Draws current matching
     *
     * @param maxMatching - current matching to draw
     * @param color
     */
    public void passCurrentMatching(HashMap<GraphNode, GraphNode> maxMatching, Color color) {
        window.getEdgeBoard().setMaxMatching(Bipartite.getMatchingList(maxMatching), color);
        window.getEdgeBoard().repaint();
    }

    public void passCurrentMatching(HashMap<GraphNode, GraphNode> maxMatching) {
        passCurrentMatching(maxMatching, Color.YELLOW);
    }

    public void stepForward() {
        HashMap<GraphNode, GraphNode> newMatching = new HashMap<>(graph.getMaxMatching());
        matchingStateStack.push(newMatching);
        inc();
        passCurrentMatching(newMatching);
    }

    public void stepBack() {
        dec();
        if (loopStep == 0) {
            begin();
            return;
        }
        matchingStateStack.pop();
        HashMap<GraphNode, GraphNode> prevMatching = matchingStateStack.peek();
        graph.setResultMatchingMap(prevMatching);
        passCurrentMatching(prevMatching);
    }

    @Deprecated
    public void log() {
        System.out.println("Loop: " + loopStep + ", stacksize: " + matchingStateStack.size());
        for (HashMap<GraphNode, GraphNode> hm : matchingStateStack) {
            for (Map.Entry<GraphNode, GraphNode> kv : hm.entrySet()) {
                System.out.println(kv.getKey() + " " + kv.getValue());
            }
            System.out.println("----");
        }
    }
}
