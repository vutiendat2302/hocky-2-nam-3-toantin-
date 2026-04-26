package assignment5_backtracking_brute_force;
import java.util.*;
import java.io.*;

public class RatInAMaze {
    /**
     * Cho ma tran nhi phan vuong mat[][] bieu dien me cung
     * Chuot xuat phat tu (0,0) va can den (n-1, n-1)
     * chuot co the di 4 huong
     * U (len), D(xuong), L (trai), R (phai)
     * Tim tat ca cac duong di hop le (khong di lai o da tham quan)
     * Neu co nhieu duong di thi in theo thu tu tu dien (dictionanry
     * tang dan
     * Neu khong co duong di thi in -1.
     * 1 la o di duoc, 0 la o bi chan
     * @param n chieu rong / chieu dai cua ma tran
     * @param maze ma tran 0/1
     *
     */


    public void findPaths(int n, int[][] maze) {
        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0) {
            System.out.println(-1);
            return;
        }

        boolean[][] visited = new boolean[n][n];
        List<String> paths = new ArrayList<>();

        visited[0][0] = true;
        dfs(0, 0, "", maze, visited, n, paths);

        if (paths.isEmpty()) {
            System.out.println(-1);
        } else {
            for (String p : paths) {
                System.out.println(p);
            }
        }
    }

    public void dfs(int row, int col, String path, int[][] maze,
                    boolean[][] visited, int n, List<String> paths) {
        if (row == n - 1 && col == n - 1) {
            paths.add(path);
            return;
        }

        int[] dr = {1, 0, 0, -1};
        int[] dc = {0, -1, 1, 0};
        char[] dir = {'D', 'L', 'R', 'U'};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                if (maze[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    dfs(newRow, newCol, path + dir[i], maze, visited, n, paths);
                    visited[newRow][newCol] = false; // Quay lui
                }
            }
        }

    }
}
