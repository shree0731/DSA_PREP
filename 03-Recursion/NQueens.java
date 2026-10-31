/**
 * @author Shreepurnaa V
 * @version 1.0
 */
import java.util.*;

/**
 * Problem: N-Queens (LeetCode #51) & N-Queens II (LeetCode #52)
 * 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other (no two queens share row, column, or diagonal).
 */
public class NQueens {

    public NQueens() {
        // Utility class
    }

    /**
     * Standard N-Queens solver with isSafe validation.
     * Places queens row-by-row and checks previous rows for column or diagonal conflicts.
     * Time Complexity: O(N!)
     * Space Complexity: O(N^2) board representation
     * 
     * @param n board dimension
     * @return all valid board configurations
     */
    public static List<List<String>> solveNQueensStandard(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) return result;

        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');

        solveStandard(board, 0, result);
        return result;
    }

    private static void solveStandard(char[][] board, int row, List<List<String>> result) {
        int n = board.length;
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';          // Place Queen
                solveStandard(board, row + 1, result); // Recurse next row
                board[row][col] = '.';          // Backtrack
            }
        }
    }

    private static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;

        // Check vertical column above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // Check top-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // Check top-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    private static List<String> constructBoard(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }

    /**
     * Optimized N-Queens with O(1) conflict lookups using boolean arrays.
     * - Column conflicts: cols[col]
     * - Main diagonal (\): (row - col + n - 1)
     * - Anti diagonal (/): (row + col)
     * 
     * Eliminates loop in isSafe() check!
     * 
     * @param n board size
     * @return all solutions
     */
    public static List<List<String>> solveNQueensOptimal(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) return result;

        boolean[] cols = new boolean[n];
        boolean[] mainDiag = new boolean[2 * n];
        boolean[] antiDiag = new boolean[2 * n];
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');

        solveOptimal(board, 0, cols, mainDiag, antiDiag, result);
        return result;
    }

    private static void solveOptimal(char[][] board, int row, boolean[] cols, boolean[] mainDiag, boolean[] antiDiag, List<List<String>> result) {
        int n = board.length;
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || mainDiag[d1] || antiDiag[d2]) continue;

            // Place Queen and mark conflicts
            board[row][col] = 'Q';
            cols[col] = true;
            mainDiag[d1] = true;
            antiDiag[d2] = true;

            solveOptimal(board, row + 1, cols, mainDiag, antiDiag, result);

            // Backtrack
            board[row][col] = '.';
            cols[col] = false;
            mainDiag[d1] = false;
            antiDiag[d2] = false;
        }
    }

    /**
     * N-Queens II (LeetCode #52: Return total number of distinct solutions).
     * Avoids board string creation entirely.
     * 
     * @param n board size
     * @return count of distinct valid solutions
     */
    public static int totalNQueens(int n) {
        if (n <= 0) return 0;
        boolean[] cols = new boolean[n];
        boolean[] mainDiag = new boolean[2 * n];
        boolean[] antiDiag = new boolean[2 * n];
        return countNQueensHelper(0, n, cols, mainDiag, antiDiag);
    }

    private static int countNQueensHelper(int row, int n, boolean[] cols, boolean[] mainDiag, boolean[] antiDiag) {
        if (row == n) return 1;
        int count = 0;

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || mainDiag[d1] || antiDiag[d2]) continue;

            cols[col] = true;
            mainDiag[d1] = true;
            antiDiag[d2] = true;

            count += countNQueensHelper(row + 1, n, cols, mainDiag, antiDiag);

            cols[col] = false;
            mainDiag[d1] = false;
            antiDiag[d2] = false;
        }
        return count;
    }

    /**
     * Visualizes an N-Queens solution with a bordered ASCII chessboard.
     */
    public static void printBoard(List<String> solution) {
        int n = solution.size();
        StringBuilder border = new StringBuilder("+");
        for (int i = 0; i < n; i++) border.append("---+");

        System.out.println(border);
        for (String row : solution) {
            System.out.print("|");
            for (char c : row.toCharArray()) {
                System.out.print(" " + c + " |");
            }
            System.out.println();
            System.out.println(border);
        }
    }

    /**
     * Bitmasking N-Queens solution counter.
     * Uses bitwise operations for ultra-high performance solution counting.
     * Time Complexity: O(N!) with minimal constant factor
     * Space Complexity: O(N) stack
     */
    public static int totalNQueensBitmask(int n) {
        if (n <= 0) return 0;
        int allOnes = (1 << n) - 1;
        return bitmaskHelper(0, 0, 0, allOnes);
    }

    private static int bitmaskHelper(int cols, int diag1, int diag2, int allOnes) {
        if (cols == allOnes) return 1;

        int count = 0;
        int available = allOnes & (~(cols | diag1 | diag2));

        while (available != 0) {
            int pick = available & (-available); // Extract lowest set bit
            available -= pick;
            count += bitmaskHelper(cols | pick, (diag1 | pick) << 1, (diag2 | pick) >>> 1, allOnes);
        }
        return count;
    }

    /**
     * Known counts for N-Queens from N=1 to N=10 for quick verification.
     */
    public static final int[] KNOWN_N_QUEENS_COUNTS = {0, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724};

    public static void main(String[] args) {
        System.out.println("=== Testing N-Queens ===");

        for (int n = 1; n <= 8; n++) {
            int count = totalNQueens(n);
            int bitCount = totalNQueensBitmask(n);
            System.out.println("N = " + n + " -> Solutions: " + count + " (Bitmask count: " + bitCount + ")");
        }

        System.out.println("\nVisualizing First Solution for 4-Queens:");
        List<List<String>> solutions4 = solveNQueensOptimal(4);
        if (!solutions4.isEmpty()) {
            printBoard(solutions4.get(0));
        }
    }
}
