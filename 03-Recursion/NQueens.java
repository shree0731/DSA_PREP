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
}
