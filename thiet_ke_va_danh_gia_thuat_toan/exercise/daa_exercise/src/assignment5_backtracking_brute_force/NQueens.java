package assignment5_backtracking_brute_force;

public class NQueens {

    private int count;
    private boolean[] col;
    private boolean[] diag1;
    private boolean[] diag2;

    /**
     * In ra so cach dat N con hau vao ban co co N x N sao cho
     * khong co con hau nao co the an duoc con hau khac
     * @param N kich thuoc ban co / so hau
     *
     */

    public void countNQueens(int N) {
        count = 0;
        col = new boolean[N];
        diag1 = new boolean[2 * N - 1];
        diag2 = new boolean[2 * N - 1];

        backtrack(0, N);
        System.out.println(count);
    }

    public void backtrack(int row, int N) {
        if (row == N) {
            count++;
            return;
        }

        for (int c = 0; c < N; c++) {
            int d1 = row - c + (N - 1);
            int d2 = row + c;

            if (!col[c] && !diag1[d1] && !diag2[d2]) {
                col[c] = true;
                diag1[d1] = true;
                diag2[d2] = true;

                backtrack(row + 1, N);

                // quay lui
                col[c] = false;
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }
}
