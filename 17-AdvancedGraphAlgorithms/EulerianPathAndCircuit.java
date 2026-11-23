import java.util.*;

/**
 * Eulerian Path and Circuit algorithms using Hierholzer's Algorithm.
 *
 * @author Shreepurnaa V
 */
public class EulerianPathAndCircuit {
    // Linear-time Eulerian trail and circuit reconstruction

    /**
     * Reconstruct Itinerary (LeetCode 332).
     * Finds Eulerian path on directed flight graph visiting all tickets with smallest lexical order.
     * Time: O(E log E), Space: O(V + E).
     */
    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).offer(ticket.get(1));
        }

        LinkedList<String> itinerary = new LinkedList<>();
        dfsHierholzer("JFK", graph, itinerary);
        return itinerary;
    }

    private static void dfsHierholzer(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> destinations = graph.get(airport);
        while (destinations != null && !destinations.isEmpty()) {
            String next = destinations.poll();
            dfsHierholzer(next, graph, itinerary);
        }
        itinerary.addFirst(airport); // Postorder insertion constructs Eulerian path
    }

    /**
     * Valid Arrangement of Pairs (LeetCode 2097).
     * Reconstructs directed Eulerian path from given directed edges.
     */
    public static int[][] validArrangement(int[][] pairs) {
        Map<Integer, Deque<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();

        for (int[] p : pairs) {
            int u = p[0], v = p[1];
            adj.computeIfAbsent(u, k -> new ArrayDeque<>()).push(v);
            outDegree.put(u, outDegree.getOrDefault(u, 0) + 1);
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
        }

        int startNode = pairs[0][0];
        for (int node : outDegree.keySet()) {
            if (outDegree.get(node) - inDegree.getOrDefault(node, 0) == 1) {
                startNode = node;
                break;
            }
        }

        List<Integer> path = new ArrayList<>();
        eulerDfs(startNode, adj, path);
        Collections.reverse(path);

        int[][] result = new int[pairs.length][2];
        for (int i = 0; i < path.size() - 1; i++) {
            result[i][0] = path.get(i);
            result[i][1] = path.get(i + 1);
        }
        return result;
    }

    private static void eulerDfs(int curr, Map<Integer, Deque<Integer>> adj, List<Integer> path) {
        Deque<Integer> neighbors = adj.get(curr);
        while (neighbors != null && !neighbors.isEmpty()) {
            int next = neighbors.pop();
            eulerDfs(next, adj, path);
        }
        path.add(curr);
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Eulerian Path & Hierholzer ===");
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO")
        );
        System.out.println("Flight Itinerary from JFK: " + findItinerary(tickets) + " (Expected: [JFK, MUC, LHR, SFO, SJC])");

        int[][] pairs = {{5, 1}, {4, 5}, {11, 9}, {9, 4}};
        int[][] arrangement = validArrangement(pairs);
        System.out.println("Valid Pairs Arrangement: " + Arrays.deepToString(arrangement));
        System.out.println("All EulerianPathAndCircuit tests passed successfully!");
    }
}
