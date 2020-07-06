package windows;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    Board (int w, int h){
        super();
        setSize(w, h);
        setBackground(Color.white);
    }
}

class BoardNode extends Board{
    BoardNode(){
        super(100, 600);

    }
}

class BoardEdge extends Board{
    BoardEdge(){
        super(300, 600);
    }
}
