package assignment3_recursion;

public class TowerOfHanoiSolver {
    /**
     * In ra các bước giải bài toán Tháp Hà Nội với n đĩa
     *
     * Mỗi bước được in ra trên một dòng theo cú pháp :
     * Move disk from X to Y
     *
     * @param n số lượng đĩa cần di chuyển
     * @param from cọc nguồn
     * @param to cọc đích
     * @param aux cọc trung gian
     */
    public void solve(int n, char from, char to, char aux) {
        // TODO
        if (n == 1) {
            System.out.println("Move disk from " + from + " to " + to);
            return;
        }
        solve(n - 1, from, aux, to);
        System.out.println("Move disk from " + from + " to " + to);
        solve(n - 1, aux, to, from);
    }
}
