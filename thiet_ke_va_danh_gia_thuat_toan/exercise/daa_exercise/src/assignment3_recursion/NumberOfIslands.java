package assignment3_recursion;

import java.util.Stack;

public class NumberOfIslands {
    /**
     * Đếm số đảo trên biển
     *
     * @param grid ma trận 0/1
     * @return số đảo
     */

    public int countIsland(int [][] grid) {
        // TODO
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    /**
     * Flood Fill để đánh dấu một đảo
     *
     * @param grid ma trận 0/1
     * @param r chỉ số hàng hiện tại
     * @param c chỉ số cột hiện tại
     */

    private void dfs(int[][] grid, int r, int c) {
        // TODO
        int n = grid.length;
        int m = grid[0].length;

        if (r < 0 || c < 0 || r >= n || c>= m) {
            return;
        }
        if (grid[r][c] == 0) {
            return;
        }
        grid[r][c] = 0;
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    public void dfs_interactive(int[][] grid, int i, int j) {
        Stack<int[]> stack = new Stack<>();
        int n = grid.length;
        int m = grid[0].length;

        stack.push(new int[]{i, j});

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int r = cur[0];
            int c = cur[1];

            if (r < 0 || c < 0 || r >= n || c >= m) continue;
            if (grid[r][c] == 0) continue;

            grid[r][c] = 0;

            // 4 hướng
            stack.push(new int[]{r + 1, c});
            stack.push(new int[]{r - 1, c});
            stack.push(new int[]{r, c + 1});
            stack.push(new int[]{r, c - 1});
        }
    }
}
