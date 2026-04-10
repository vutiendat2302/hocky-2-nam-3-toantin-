package assignment7_greedy_methods;

import java.util.Arrays;

public class KruskalMST {
    /**
     * Tim cay bao trum nho nhat bang thuat toan Kruskal
     *
     * @param n so dinh (tu 0 toi n - 1)
     * @param edges danh sach canh, moi canh dang [u, v, w]
     * @return danh sach canh cua MST (mang kich thuoc n - 1, moi phan tu [u, v, w]
     */

    public int[][] kruskal(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        return new int[n - 1][3];
    }
}
