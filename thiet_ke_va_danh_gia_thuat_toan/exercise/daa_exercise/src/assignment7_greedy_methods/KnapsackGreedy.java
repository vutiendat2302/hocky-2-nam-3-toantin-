package assignment7_greedy_methods;

import java.util.Arrays;

public class KnapsackGreedy {
    /**
     * Ban co mot chiec balo  voi suc chua gioi han (capacity)
     * Co n mon do, moi mon co khoi luong weight, va co gia tri value
     *
     * Muc tieu:
     * Chon cac mon do de tong gia tri la lon nhat, nhung tong khoi luong khong vuot qua capacity
     *
     *
     * Y tuong tham lam:
     * Thay vi xet toan bo tong hop, ta chon cac mon co ty le value/weight lon nhat truoc
     *
     *
     * @param weights mang khoi luong
     * @param values mang gia tri
     * @param capacity suc chua balo
     * @return tonng gia tri dat duoc
     */

    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // Tao mang 2 chieu de quan ly weight, value, ratios
        double[][] arr = new double[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = weights[i];
            arr[i][1] = values[i];
            arr[i][2] = (double) values[i] / weights[i];
        }
        sort(arr, n);
        int total_value = 0;
        for (int i = 0; i < n; i++) {
            int w = (int) arr[i][0];
            int v = (int) arr[i][1];
            if (capacity >= w) {
                total_value += v;
                capacity -= w;
            }
        }

        return total_value;
    }

    public void sort(double[][] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j][2] < arr[j + 1][2]) {
                    double[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static class TestKnapsack {
        public static void main(String[] args) {
            KnapsackGreedy solver = new KnapsackGreedy();

            System.out.println("=== TESTING KNAPSACK GREEDY ===");

            // Test Case 1: Trường hợp cơ bản (Tham lam hoạt động tốt)
            // Món 1: 10kg, $60 (Ratio 6)
            // Món 2: 20kg, $100 (Ratio 5)
            // Balo: 25kg => Nên lấy Món 1 (10kg) + một phần còn lại hoặc xét tiếp
            // Ở đây 0/1 Knapsack sẽ lấy Món 1 (10kg, $60), còn thừa 15kg không đủ lấy Món 2.
            int[] w1 = {10, 20};
            int[] v1 = {60, 100};
            int cap1 = 25;
            System.out.println("Test 1 (Basic): " + (solver.knapsack(w1, v1, cap1) == 60 ? "PASSED" : "FAILED"));

            // Test Case 2: Mảng chưa sắp xếp (Kiểm tra hàm sort của mày)
            // Món A: 30kg, $120 (Ratio 4)
            // Món B: 10kg, $60 (Ratio 6) -> Ngon nhất
            // Món C: 20kg, $100 (Ratio 5)
            // Balo: 35kg.
            // Thứ tự ưu tiên: B (10kg, $60) -> C (20kg, $100) -> A (30kg, $120)
            // Lấy B và C: Tổng 30kg, giá trị $160. Còn dư 5kg không lấy được A.
            int[] w2 = {30, 10, 20};
            int[] v2 = {120, 60, 100};
            int cap2 = 35;
            int result2 = solver.knapsack(w2, v2, cap2);
            System.out.println("Test 2 (Unsorted): " + (result2 == 160 ? "PASSED" : "FAILED") + " (Result: " + result2 + ")");

            // Test Case 3: Sức chứa bằng đúng khối lượng (Kiểm tra dấu >=)
            int[] w3 = {15, 10};
            int[] v3 = {100, 50};
            int cap3 = 15;
            System.out.println("Test 3 (Boundary): " + (solver.knapsack(w3, v3, cap3) == 100 ? "PASSED" : "FAILED"));

            // Test Case 4: Trường hợp Tham lam THẤT BẠI (Để mày hiểu bản chất)
            // Balo: 50kg
            // Món 1: 10kg, $60 (Ratio 6)
            // Món 2: 20kg, $100 (Ratio 5)
            // Món 3: 30kg, $120 (Ratio 4)
            // Tham lam sẽ nhặt: Món 1 + Món 2 = 30kg, $160. (Hết chỗ cho món 3)
            // Tối ưu thực tế (DP): Món 2 + Món 3 = 50kg, $220.
            int[] w4 = {10, 20, 30};
            int[] v4 = {60, 100, 120};
            int cap4 = 50;
            int result4 = solver.knapsack(w4, v4, cap4);
            System.out.println("Test 4 (Greedy Limitation): Result = " + result4 + " (Tối ưu thực tế phải là 220)");

            // Test Case 5: Balo không chứa nổi món nào
            int[] w5 = {10, 20};
            int[] v5 = {100, 200};
            int cap5 = 5;
            System.out.println("Test 5 (Capacity too small): " + (solver.knapsack(w5, v5, cap5) == 0 ? "PASSED" : "FAILED"));
        }
    }


    //Key
    /**
     * public int knapsack(int[] weights, int[] values, int capacity) {
     *     int n = weights.length;
     *     boolean[] used = new boolean[n]; // Mảng đánh dấu
     *     int totalValue = 0;
     *
     *     for (int i = 0; i < n; i++) {
     *         int bestIndex = -1;
     *         double maxRatio = -1.0;
     *
     *         // Tìm món đồ "hời" nhất mà chưa được sử dụng
     *         for (int j = 0; j < n; j++) {
     *             if (!used[j]) {
     *                 double currentRatio = (double) values[j] / weights[j];
     *                 if (currentRatio > maxRatio) {
     *                     maxRatio = currentRatio;
     *                     bestIndex = j;
     *                 }
     *             }
     *         }
     *
     *         // Nếu không tìm thấy món nào nữa thì thoát
     *         if (bestIndex == -1) break;
     *
     *         // Đánh dấu đã xem xét món này
     *         used[bestIndex] = true;
     *
     *         // Kiểm tra sức chứa và nhặt đồ
     *         if (capacity >= weights[bestIndex]) {
     *             totalValue += values[bestIndex];
     *             capacity -= weights[bestIndex];
     *         }
     *     }
     *
     *     return totalValue;
     * }
     */
}

