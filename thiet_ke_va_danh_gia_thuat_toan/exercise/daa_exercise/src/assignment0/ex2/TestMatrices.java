package assignment0.ex2;

public class TestMatrices {
    /**
     *  Cho hai mảng hai chiều A và B biểu diễn hai ma trận vuông kích thước n × n.
     *
     * a, Hãy tính tích ma trận AB.
     * Đo thời gian thực thi của thuật toán nhân ma trận với các giá trị:
     * n = 10, 20, 30, 40, 50
     * Báo cáo thời gian chạy tương ứng với từng kích thước ma trận
     * Nhận xét xu hướng tăng trưởng thời gian khi n tăng
     *
     * b, Cho một mảng hai chiều biểu diễn một ma trận.
     *
     * Hãy in các phần tử theo thứ tự xoắn ốc (spiral order)
     * Bắt đầu từ góc trên bên trái và đi theo chiều kim đồng hồ
     */

    public static int[][] multiply(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    static int[][] randomMatrix(int n) {
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                M[i][j] = (int)(Math.random() * 10);
        return M;
    }

    static void spiralOrder(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++)       System.out.print(matrix[top][i] + " ");
            top++;
            for (int i = top; i <= bottom; i++)       System.out.print(matrix[i][right] + " ");
            right--;
            if (top <= bottom)
                for (int i = right; i >= left; i--)   System.out.print(matrix[bottom][i] + " ");
            bottom--;
            if (left <= right)
                for (int i = bottom; i >= top; i--)   System.out.print(matrix[i][left] + " ");
            left++;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        // a. Đo thời gian nhân ma trận
        int[] sizes = {10, 20, 30, 40, 50};
        System.out.println("=== Nhân ma trận ===");
        System.out.printf("%-5s %s%n", "n", "Thời gian (ms)");

        for (int n : sizes) {
            // Warm up
            for (int w = 0; w < 3; w++) multiply(randomMatrix(n), randomMatrix(n), n);

            int runs = 10;
            long total = 0;
            for (int r = 0; r < runs; r++) {
                int[][] A = randomMatrix(n); // random ma trận mới mỗi lần
                int[][] B = randomMatrix(n);

                long start = System.nanoTime();
                multiply(A, B, n);
                total += System.nanoTime() - start;
            }

            System.out.printf("%-5d %.4f ms%n", n, total / runs / 1_000_000.0);
        }

        // b. In xoắn ốc
        System.out.println("\n=== Xoắn ốc ===");
        int[][] matrix = {
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 16}
        };
        spiralOrder(matrix);
        // Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
    }
}
