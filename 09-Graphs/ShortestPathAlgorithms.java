import java.util.*;

/**
 * Shortest path algorithms across weighted, unweighted, and negative-weight graphs.
 * Implements Dijkstra, Bellman-Ford, Floyd-Warshall, and Grid Shortest Path.
 *
 * @author Shreepurnaa V
 */
public class ShortestPathAlgorithms {

    public static class Edge {
        public final int to;
        public final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
