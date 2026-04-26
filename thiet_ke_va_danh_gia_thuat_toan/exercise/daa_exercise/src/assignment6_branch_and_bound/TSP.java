package assignment6_branch_and_bound;

import java.util.List;
import java.util.*;

public class TSP {

    private int n;
    private int[][] cost ;
    private int bestCost;
    private List<Integer> bestPath;

    // bai toan nguoi giao hang
    /**
     * Co n thanh pho danh so tu 0 -> n - 1
     * cost[i][j] la chi phi di tu thanh pho i den thanh pho j
     *
     * nguoi giao hang xuat phat tu thanh pho 0
     * di qua tat ca cac thanh pho dung 1 lan
     * roi quay tro lai thanh pho 0
     *
     * yeu cau:
     * - dong1: in ra tong chi phi nho nhat
     * - dong2: in ra duong di (bao gom ca quay ve 0)
     *
     * @param n so luong thanh pho
     * @param cost ma tran chi phi kich thuoc n x n
     */

    public void solveTSP(int n, int[][] cost) {
        this.n = n;
        this.cost = cost;
        bestCost = Integer.MAX_VALUE;
        bestPath = new ArrayList<>();

        boolean[] visited = new boolean[n];
        visited[0] = true;

        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0);

        branchAndBound(0, 1, 0, visited, currentPath);

        System.out.println(bestCost);
        for (int i = 0; i < bestPath.size(); i++) {
            System.out.print(bestPath.get(i));
            if (i < bestPath.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }

    public void branchAndBound(int currentCity, int visitedCount, int currentCost, boolean[] visited, List<Integer> path) {
        if (visitedCount == n) {
            int totalCost = currentCost + cost[currentCity][0];
            if (totalCost < bestCost) {
                bestCost = totalCost;
                bestPath = new ArrayList<>(path);
                bestPath.add(0);
            }
            return;
        }

        int bound = currentCost;
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                int minOut = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (i != j && (!visited[j] || j == 0)) {
                        minOut = Math.min(minOut, cost[i][j]);
                    }
                }
                bound += minOut;
            }
        }

        if (bound >= bestCost) {
            return;
        }

        for (int nextCity = 0; nextCity < n; nextCity++)  {
            if (!visited[nextCity]) {
                visited[nextCity] = true;
                path.add(nextCity);

                branchAndBound(nextCity, visitedCount + 1, currentCost + cost[currentCity][nextCity], visited, path);

                visited[nextCity] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
