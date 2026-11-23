import java.util.*;

/**
 * Eulerian Path and Circuit algorithms using Hierholzer's Algorithm.
 *
 * @author Shreepurnaa V
 */
public class EulerianPathAndCircuit {

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
}
