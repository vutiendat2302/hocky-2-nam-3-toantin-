package assignment3_recursion;

import java.util.List;
import java.util.Arrays;

public class Test{

    public static void main(String[] args) {
        System.out.println("========== BÀI TẬP 3: ĐỆ QUY VÀ KHỬ ĐỆ QUY ==========\n");

        testDecimalToBinary();
        testFibonacci();
        testGCD();
        testPrimeFactorization();
        testTowerOfHanoi();
        testRewardDistribution();
        testNumberOfIslands();
        testKnapsack();

        System.out.println("\n========== KẾT THÚC TEST ==========");
    }

    // ==================== 1. TEST DECIMAL TO BINARY ====================
    private static void testDecimalToBinary() {
        System.out.println("--- 1. Chuyển đổi thập phân sang nhị phân ---");
        DecimalToBinary converter = new DecimalToBinary();

        int[] testNumbers = {0, 1, 2, 5, 10, 16, 31, 42, 100, 255};

        System.out.println("Số thập phân | Nhị phân (đệ quy) | Nhị phân (lặp) | Khớp?");
        System.out.println("-----------------------------------------------------");

        boolean allMatch = true;
        for (int n : testNumbers) {
            String recResult = converter.convertRecursive(n);
            String iterResult = converter.convertIterative(n);
            boolean match = recResult.equals(iterResult);
            allMatch &= match;
            System.out.printf("%12d | %16s | %14s | %s\n", n, recResult, iterResult, match ? "✓" : "✗");
        }
        System.out.println("Kết quả: " + (allMatch ? "TẤT CẢ ĐỀU ĐÚNG ✓\n" : "CÓ LỖI ✗\n"));
    }

    // ==================== 2. TEST FIBONACCI ====================
    private static void testFibonacci() {
        System.out.println("--- 2. Dãy số Fibonacci ---");
        Fibonacci fib = new Fibonacci();

        int[] testNumbers = {0, 1, 2, 5, 10, 15, 20, 30};

        System.out.println("n | F(n) đệ quy | F(n) lặp | Thời gian đệ quy | Thời gian lặp");
        System.out.println("-------------------------------------------------------------");

        for (int n : testNumbers) {
            // Đo thời gian đệ quy
            long startRec = System.nanoTime();
            int recResult = fib.fibonacciRecursive(n);
            long endRec = System.nanoTime();
            double timeRec = (endRec - startRec) / 1_000_000.0;

            // Đo thời gian lặp
            long startIter = System.nanoTime();
            int iterResult = fib.fibonacciIterative(n);
            long endIter = System.nanoTime();
            double timeIter = (endIter - startIter) / 1_000_000.0;

            boolean match = (recResult == iterResult);
            System.out.printf("%d | %10d | %9d | %12.3f ms | %10.3f ms | %s\n",
                    n, recResult, iterResult, timeRec, timeIter, match ? "✓" : "✗");
        }

        // Test với n lớn hơn (chỉ lặp)
        System.out.println("\nTest với n lớn (chỉ phương pháp lặp):");
        int[] largeTests = {40, 45, 50};
        for (int n : largeTests) {
            long start = System.nanoTime();
            int result = fib.fibonacciIterative(n);
            long end = System.nanoTime();
            System.out.printf("F(%d) = %d (thời gian: %.3f ms)\n", n, result, (end - start) / 1_000_000.0);
        }
        System.out.println();
    }

    // ==================== 3. TEST GCD ====================
    private static void testGCD() {
        System.out.println("--- 3. Tìm UCLN (Euclid) ---");
        GCD gcd = new GCD();

        int[][] testPairs = {
                {12, 18}, {24, 36}, {17, 19}, {100, 25}, {0, 5}, {5, 0}, {48, 180}
        };

        System.out.println("(a, b) | UCLN đệ quy | UCLN lặp | Khớp?");
        System.out.println("----------------------------------------");

        boolean allMatch = true;
        for (int[] pair : testPairs) {
            int a = pair[0];
            int b = pair[1];
            int recResult = gcd.findGcdRecursive(a, b);
            int iterResult = gcd.findGcdIterative(a, b);
            boolean match = (recResult == iterResult);
            allMatch &= match;
            System.out.printf("(%2d,%2d) | %10d | %9d | %s\n", a, b, recResult, iterResult, match ? "✓" : "✗");
        }
        System.out.println("Kết quả: " + (allMatch ? "TẤT CẢ ĐỀU ĐÚNG ✓\n" : "CÓ LỖI ✗\n"));
    }

    // ==================== 4. TEST PRIME FACTORIZATION ====================
    private static void testPrimeFactorization() {
        System.out.println("--- 4. Phân tích thừa số nguyên tố ---");
        PrimeFactorization pf = new PrimeFactorization();

        int[] testNumbers = {2, 3, 4, 6, 12, 18, 30, 64, 100, 97, 210};

        System.out.println("Số | Thừa số (đệ quy) | Thừa số (lặp) | Khớp?");
        System.out.println("------------------------------------------------");

        boolean allMatch = true;
        for (int n : testNumbers) {
            List<Integer> recResult = pf.factorRecursive(n);
            List<Integer> iterResult = pf.factorIterative(n);
            boolean match = recResult.equals(iterResult);
            allMatch &= match;
            System.out.printf("%3d | %-16s | %-13s | %s\n", n, recResult.toString(), iterResult.toString(), match ? "✓" : "✗");
        }
        System.out.println("Kết quả: " + (allMatch ? "TẤT CẢ ĐỀU ĐÚNG ✓\n" : "CÓ LỖI ✗\n"));
    }

    // ==================== 5. TEST TOWER OF HANOI ====================
    private static void testTowerOfHanoi() {
        System.out.println("--- 5. Tháp Hà Nội ---");
        TowerOfHanoiSolver solver = new TowerOfHanoiSolver();

        int[] testSizes = {1, 2, 3};

        for (int n : testSizes) {
            System.out.println("\nVới n = " + n + " đĩa:");
            System.out.println("Các bước di chuyển:");
            solver.solve(n, 'A', 'C', 'B');
            System.out.println("---");
        }

        // Đo thời gian cho n lớn hơn
        System.out.println("\nĐo thời gian với n = 15 (số bước = 32767):");
        long start = System.nanoTime();
        // solver.solve(15, 'A', 'C', 'B'); // Bỏ comment nếu muốn chạy
        long end = System.nanoTime();
        System.out.println("(Đã comment để tránh in quá nhiều) Thời gian ước tính: ~" + (end - start) / 1_000_000.0 + " ms\n");
    }

    // ==================== 6. TEST REWARD DISTRIBUTION ====================
    private static void testRewardDistribution() {
        System.out.println("--- 6. Bài toán chia phần thưởng ---");

        int[][] testCases = {
                {3, 5},   // n=3, m=5 → 5 cách
                {4, 6},   // n=4, m=6 → 9 cách
                {5, 8},   // n=5, m=8 → 18 cách
                {4, 10},  // n=4, m=10 → 23 cách
                {3, 3}    // n=3, m=3 → 3 cách: (3,0,0), (2,1,0), (1,1,1)
        };

        System.out.println("n\tm\tKết quả (đệ quy)\tThời gian");
        System.out.println("----------------------------------------");

        for (int[] test : testCases) {
            int n = test[0];
            int m = test[1];

            long start = System.nanoTime();
            int result = RewardDistribution.rewardRecursive(n, m);
            long end = System.nanoTime();
            double time = (end - start) / 1_000_000.0;

            System.out.printf("%d\t%d\t%d\t\t%.3f ms\n", n, m, result, time);
        }
    }

    // ==================== 7. TEST NUMBER OF ISLANDS ====================
    private static void testNumberOfIslands() {
        System.out.println("--- 7. Đếm số đảo (Number of Islands) ---");
        NumberOfIslands islandCounter = new NumberOfIslands();

        // Test case 1
        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
        };

        // Test case 2
        int[][] grid2 = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        // Test case 3: không có đảo
        int[][] grid3 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        // Test case 4: toàn bộ là đảo
        int[][] grid4 = {
                {1, 1},
                {1, 1}
        };

        System.out.println("Test case 1 (dự đoán: 3 đảo):");
        printGrid(grid1);
        int result1 = islandCounter.countIsland(copyGrid(grid1));
        System.out.println("Số đảo: " + result1 + (result1 == 3 ? " ✓" : " ✗"));

        System.out.println("\nTest case 2 (dự đoán: 1 đảo):");
        printGrid(grid2);
        int result2 = islandCounter.countIsland(copyGrid(grid2));
        System.out.println("Số đảo: " + result2 + (result2 == 1 ? " ✓" : " ✗"));

        System.out.println("\nTest case 3 (dự đoán: 0 đảo):");
        printGrid(grid3);
        int result3 = islandCounter.countIsland(copyGrid(grid3));
        System.out.println("Số đảo: " + result3 + (result3 == 0 ? " ✓" : " ✗"));

        System.out.println("\nTest case 4 (dự đoán: 1 đảo):");
        printGrid(grid4);
        int result4 = islandCounter.countIsland(copyGrid(grid4));
        System.out.println("Số đảo: " + result4 + (result4 == 1 ? " ✓" : " ✗"));

        System.out.println("\nKiểm tra flood fill bằng stack (dfs_interactive):");
        int[][] testGrid = {
                {1, 1, 0},
                {1, 0, 1},
                {0, 1, 1}
        };
        System.out.println("Ma trận ban đầu:");
        printGrid(testGrid);
        islandCounter.dfs_interactive(testGrid, 0, 0);
        System.out.println("Sau khi flood fill từ (0,0):");
        printGrid(testGrid);
        System.out.println();
    }

    // ==================== 8. TEST KNAPSACK ====================
    private static void testKnapsack() {
        System.out.println("--- 8. Bài toán cái túi (Knapsack) ---");
        Knapsack ks = new Knapsack();

        // Test case 1
        int[] P1 = {2, 3, 4, 5};
        int[] V1 = {3, 4, 5, 6};
        int M1 = 5;

        // Test case 2
        int[] P2 = {1, 2, 3, 5};
        int[] V2 = {1, 6, 10, 16};
        int M2 = 7;

        // Test case 3
        int[] P3 = {10, 20, 30};
        int[] V3 = {60, 100, 120};
        int M3 = 50;

        System.out.println("Test case 1: M=5, P=[2,3,4,5], V=[3,4,5,6]");
        int result1 = ks.knapsackRecursive(4, M1, P1, V1);
        System.out.println("Giá trị lớn nhất: " + result1 + " (dự đoán: 7) " + (result1 == 7 ? "✓" : "✗"));
        System.out.print("Các vật được chọn: ");
        ks.traceItems(4, M1, P1, V1);

        System.out.println("\nTest case 2: M=7, P=[1,2,3,5], V=[1,6,10,16]");
        int result2 = ks.knapsackRecursive(4, M2, P2, V2);
        System.out.println("Giá trị lớn nhất: " + result2 + " (dự đoán: 22) " + (result2 == 22 ? "✓" : "✗"));
        System.out.print("Các vật được chọn: ");
        ks.traceItems(4, M2, P2, V2);

        System.out.println("\nTest case 3: M=50, P=[10,20,30], V=[60,100,120]");
        int result3 = ks.knapsackRecursive(3, M3, P3, V3);
        System.out.println("Giá trị lớn nhất: " + result3 + " (dự đoán: 220) " + (result3 == 220 ? "✓" : "✗"));
        System.out.print("Các vật được chọn: ");
        ks.traceItems(3, M3, P3, V3);

        System.out.println("\n⚠️ LƯU Ý: KnapsackRecursive là O(2^n), chỉ nên test với n ≤ 20");
        System.out.println("   Để tối ưu, nên dùng quy hoạch động O(n×M)\n");
    }

    // ==================== UTILITY METHODS ====================

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private static int[][] copyGrid(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}