package tests;

import algo.Bipartite;
import algo.Edge;
import parser.ItemData;
import parser.MatchingData;

import java.util.ArrayList;

public class BipartiteTest {

    /*public static Bipartite getFullBip3() {
        ArrayList<MatchingData> data = new ArrayList<>();
        data.add(new MatchingData(new ItemData(1, "1"), new ItemData[] {new ItemData(4, "4"), new ItemData(5, "5"), new ItemData(6, "6")}));
        data.add(new MatchingData(new ItemData(2, "2"), new ItemData[] {new ItemData(4, "4"), new ItemData(5, "5"), new ItemData(6, "6")}));
        data.add(new MatchingData(new ItemData(3, "3"), new ItemData[] {new ItemData(4, "4"), new ItemData(5, "5"), new ItemData(6, "6")}));
        Bipartite bip = new Bipartite(
                data
        );
        return bip;
    }

    public static void createAndPrintSimpleBipartite() {
        Bipartite bip = getFullBip3();

        System.out.println(bip.toString());
        System.out.println("Max matching:");
        for (Edge currentEdge : bip.getMaxMatching()) {
            System.out.println(currentEdge.toString());
        }
    }*/
}
