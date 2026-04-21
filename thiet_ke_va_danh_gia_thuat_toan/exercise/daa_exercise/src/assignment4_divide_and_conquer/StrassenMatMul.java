package assignment4_divide_and_conquer;

public class StrassenMatMul {
    /**
     * viet chuong trinh nhan ma tran Strassen cho cac ma tran vuong co n x n
     * Tra ve ma trna ket qua
     * @param n co cua ma tran
     * @param a ma tran a
     * @param b ma tran b
     */

    public void strassenMatMul(int n, int[][] a, int[][] b) {
        int[][] result = strassen(a, b);
        // In kết quả (hoặc gán vào biến toàn cục)
        System.out.println("Result:");
        printMatrix(result);
    }

    public int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;

        // Base case: ma trận 1x1
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        // Bước 1: Chia ma trận thành 4 khối con
        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];
        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        split(A, A11, 0, 0);
        split(A, A12, 0, newSize);
        split(A, A21, newSize, 0);
        split(A, A22, newSize, newSize);

        split(B, B11, 0, 0);
        split(B, B12, 0, newSize);
        split(B, B21, newSize, 0);
        split(B, B22, newSize, newSize);

        int[][] P1 = strassen(add(A11, A22), add(B11, B22));        // P1 = (A11+A22)*(B11+B22)
        int[][] P2 = strassen(add(A21, A22), B11);                  // P2 = (A21+A22)*B11
        int[][] P3 = strassen(A11, subtract(B12, B22));             // P3 = A11*(B12-B22)
        int[][] P4 = strassen(A22, subtract(B21, B11));             // P4 = A22*(B21-B11)
        int[][] P5 = strassen(add(A11, A12), B22);                  // P5 = (A11+A12)*B22
        int[][] P6 = strassen(subtract(A21, A11), add(B11, B12));   // P6 = (A21-A11)*(B11+B12)
        int[][] P7 = strassen(subtract(A12, A22), add(B21, B22));   // P7 = (A12-A22)*(B21+B22)

        int[][] C11 = add(subtract(add(P1, P4), P5), P7);           // C11 = P1 + P4 - P5 + P7
        int[][] C12 = add(P3, P5);                                  // C12 = P3 + P5
        int[][] C21 = add(P2, P4);                                  // C21 = P2 + P4
        int[][] C22 = add(subtract(add(P1, P3), P2), P6);           // C22 = P1 + P3 - P2 + P6

        // Bước 4: Ghép 4 khối lại thành ma trận kết quả
        int[][] C = new int[n][n];
        join(C11, C, 0, 0);
        join(C12, C, 0, newSize);
        join(C21, C, newSize, 0);
        join(C22, C, newSize, newSize);

        return C;
    }

    public int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];

            }
        }
        return C;
    }

    public int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];

            }
        }
        return C;
    }

    public void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                C[i][j] = P[i + iB][j + jB];
            }
        }
    }

    public void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                P[i + iB][j + jB] = C[i][j];
            }
        }
    }

    private void printMatrix(int[][] M) {
        for (int[] row : M) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }
}
