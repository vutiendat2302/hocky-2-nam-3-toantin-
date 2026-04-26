package assignment6_branch_and_bound;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Knapsack {

    /**
     * Cho n vat, mot vat co:
     * weight[i]: trong luong vat thu i
     * value[i]: gia tri vat thu i
     *
     * cho ba lo co suc chua toi da W
     * Moi vat chi duoc chon toi da 1 lan
     * Hay:
     * dong1: in ra tong gia tri lon nhat co the dat duoc ma khong vuot qua W
     * dong2: in ra mang 0/1, 0 nghia la vat thu i khong duoc lay, 1 nguoc lai
     *
     * @param n so luong do vat
     * @param W suc chua balo
     * @param weight mang trong luong
     * @param value mang gia tri
     */

    int n, W;
    int[] weight, value;
    double[] ratio;
    int[] index;

    int bestValue = 0;
    int[] bestSelection;
    int[] currentSelection;

    public void solveKnapsack(int n, int W, int[] weight, int[] value) {
        this.n = n;
        this.W = W;
        this.weight = weight.clone();
        this.value = value.clone();

        ratio = new double[n];
        index = new int[n];

        for (int i = 0; i < n; i++) {
            ratio[i] = (double) value[i] / weight[i];
            index[i] = i;
        }

        // sort theo ratio giảm dần
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ratio[j] > ratio[i]) {
                    swap(i, j);
                }
            }
        }

        bestSelection = new int[n];
        currentSelection = new int[n];

        backtrack(0, 0, 0);

        // in kết quả
        System.out.println(bestValue);

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[index[i]] = bestSelection[i];
        }

        for (int i = 0; i < n; i++) {
            System.out.print(result[i] + " ");
        }
    }

    void backtrack(int level, int currentWeight, int currentValue) {
        if (level == n) {
            if (currentValue > bestValue) {
                bestValue = currentValue;
                System.arraycopy(currentSelection, 0, bestSelection, 0, n);
            }
            return;
        }

        double bound = computeBound(level, currentWeight, currentValue);
        if (bound <= bestValue) return; // cắt nhánh

        // Nhánh 1: chọn
        if (currentWeight + weight[level] <= W) {
            currentSelection[level] = 1;
            backtrack(level + 1,
                    currentWeight + weight[level],
                    currentValue + value[level]);
        }

        // Nhánh 2: không chọn
        currentSelection[level] = 0;
        backtrack(level + 1, currentWeight, currentValue);
    }

    double computeBound(int level, int currentWeight, int currentValue) {
        if (currentWeight >= W) return 0;

        double bound = currentValue;
        int remaining = W - currentWeight;

        int i = level;
        while (i < n && weight[i] <= remaining) {
            remaining -= weight[i];
            bound += value[i];
            i++;
        }

        if (i < n) {
            bound += ratio[i] * remaining;
        }

        return bound;
    }

    void swap(int i, int j) {
        int tmp;

        tmp = weight[i]; weight[i] = weight[j]; weight[j] = tmp;
        tmp = value[i]; value[i] = value[j]; value[j] = tmp;

        double t = ratio[i]; ratio[i] = ratio[j]; ratio[j] = t;

        tmp = index[i]; index[i] = index[j]; index[j] = tmp;
    }
}
