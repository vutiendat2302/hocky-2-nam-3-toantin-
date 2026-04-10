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

    }

    public int[][] strassen(int[][] A, int[][] B) {
        int[][] arr = new int[2][3];
        return arr;
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
}
