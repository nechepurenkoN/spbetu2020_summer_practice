package utils;

import algo.Edge;
import windows.VisualWindow;

import java.awt.*;
import java.util.ArrayList;

public class Mediator {
    VisualWindow window;
    Integer loopStep;

    public Mediator(VisualWindow window) {
        this.window = window;
        loopStep = 0;
    }

    public void reset() {
        loopStep = 0;
    }

    public void inc() {
        loopStep++;
    }

    public int getCurrentStep() {
        return loopStep;
    }

    public void stop() {
        reset();
        window.setButtonActive(window.getButtonPanel().getMaxMatching());
        window.setButtonInActive(window.getButtonPanel().getStep());
    }

    public void passCurrentMatching(ArrayList<Edge> maxMatching) {
        window.getEdgeBoard().setMaxMatching(maxMatching, Color.YELLOW);
        window.getEdgeBoard().repaint();
    }

    public void drawTemporaryEdge(Edge temporaryEdge) {
        window.getEdgeBoard().drawEdge(temporaryEdge, Color.BLUE);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.getEdgeBoard().drawEdge(temporaryEdge, Color.BLACK);
    }
}
