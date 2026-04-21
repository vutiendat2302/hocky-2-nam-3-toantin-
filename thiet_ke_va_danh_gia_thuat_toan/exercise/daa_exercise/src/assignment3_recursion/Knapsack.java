package assignment3_recursion;

import java.util.List;

public class Knapsack {
    /**
     * Co n vat pham
     * Moi vat co: trong luong pi se co
     * Gia tri: vi
     *
     * Mot cai tui co suc chua M
     *
     * Tim tong gia tri lon nhat
     * Xac dinh cac vat duoc chon
     *
     * @param n so vat pham
     * @param M suc chua M
     * @param P trong luong
     * @param V gia tri
     * @return S tong gia tri lon nhat
     * @return arr danh sach cac vat duoc chon
     *
     */
    public int knapsackRecursive(int n, int M, int[] P, int[] V) {
        if (n == 0 || M == 0) {
            return 0;
        }
        if (P[n - 1] > M) {
            return knapsackRecursive(n - 1, M, P, V);
        }

        return Math.max(knapsackRecursive(n - 1, M, P, V), V[n - 1] + knapsackRecursive(n - 1, M - P[n - 1], P, V));
    }

    public void traceItems(int n, int M, int[] P, int[] V) {
        if (n == 0 || M == 0) {
            return;
        }

        if (P[n - 1] > M) {
            traceItems(n - 1, M, P, V);
        } else {
            int without = knapsackRecursive(n - 1, M, P, V);
            int with = V[n - 1] + knapsackRecursive(n - 1, M - P[n - 1], P, V);

            if (with > without) {
                System.out.println("Chọn vật: " + (n - 1));
                traceItems(n - 1, M - P[n - 1], P, V);
            } else {
                traceItems(n - 1, M, P, V);
            }
        }
    }


}
