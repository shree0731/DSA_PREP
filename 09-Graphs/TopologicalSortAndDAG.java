import java.util.*;

/**
 * Topological sorting and Directed Acyclic Graph (DAG) dependency resolution.
 * Implements Kahn's Algorithm (in-degree BFS), DFS Reverse Postorder, Course Schedule, and Alien Dictionary.
 *
 * @author Shreepurnaa V
 */
public class TopologicalSortAndDAG {


    /**
     * Kahn's Algorithm for Topological Sort using In-Degree Queue.
     * Time: O(V + E), Space: O(V).
     * If output length < vertices, graph contains a cycle!
     */
    public static int[] topoSortKahn(int vertices, List<List<Integer>> adj) {
        int[] inDegree = new int[vertices];
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[vertices];
        int idx = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[idx++] = u;

            for (int v : adj.get(u)) {
                if (--inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return idx == vertices ? order : new int[0]; // Empty array if cycle detected
    }

    /**
     * Topological Sort using DFS Reverse Postorder.
     * Vertices pushed to stack after all descendants are visited.
     * Time: O(V + E), Space: O(V).
     */
    public static int[] topoSortDFS(int vertices, List<List<Integer>> adj) {
        boolean[] visited = new boolean[vertices];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsTopo(i, adj, visited, stack);
            }
        }

        int[] order = new int[vertices];
        int idx = 0;
        while (!stack.isEmpty()) {
            order[idx++] = stack.pop();
        }
        return order;
    }

    private static void dfsTopo(int u, List<List<Integer>> adj, boolean[] visited, Deque<Integer> stack) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfsTopo(v, adj, visited, stack);
            }
        }
        stack.push(u); // Reverse postorder
    }

    /**
     * Course Schedule I (LeetCode 207).
     * Determines whether all courses can be finished without cyclical prerequisites.
     */
    public static boolean canFinishCourses(int numCourses, int[][] prerequisites) {
        return findOrderCourses(numCourses, prerequisites).length == numCourses;
    }

    /**
     * Course Schedule II (LeetCode 210).
     * Returns valid course ordering if possible; otherwise returns empty array.
     */
    public static int[] findOrderCourses(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] inDegree = new int[numCourses];

        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]); // p[1] -> p[0]
            inDegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[numCourses];
        int idx = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[idx++] = curr;

            for (int next : adj.get(curr)) {
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return idx == numCourses ? order : new int[0];
    }

    /**
     * Alien Dictionary (LeetCode 269).
     * Derives unique alphabetical character ordering from sorted foreign words.
     * Time: O(Total Characters), Space: O(1) bounded by alphabet size 26.
     */
    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        for (String w : words) {
            for (char c : w.toCharArray()) {
                adj.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            // Fix: Invalid prefix order edge case (e.g., ["abc", "ab"])
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";

            int minLen = Math.min(w1.length(), w2.length());
            for (int j = 0; j < minLen; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    if (adj.get(c1).add(c2)) {
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) queue.offer(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            sb.append(curr);
            for (char next : adj.get(curr)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) queue.offer(next);
            }
        }

        return sb.length() == inDegree.size() ? sb.toString() : "";
    }
}
