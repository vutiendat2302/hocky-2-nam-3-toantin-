package assignment6_branch_and_bound;

public class AssignmentProblem {

    private int n;
    private int[][] cost;
    private int bestCost;
    private int[] bestAssignment;
    private int[] currentAssignment;
    private boolean[] assignedJobs;

    /**
     * Co n nhan vien va n cong viec
     * cost[i][j] la chi phi khi nhan vien i lam cong viec j
     *
     * Moi nhan vien duoc phan cong dung 1 cong viec
     * Moi cong viec chi duoc giao cho dung mot nhan vien
     *
     * Yeu cau:
     * - dong 1: in ra tong chi phi nho nhat
     * - dong 2: in ra mang assignment
     *
     * @param n so luong nhan vien(cung la so luong cong viec)
     * @param cost ma tran chi phi kich thuoc n x n
     */

    public void solveAssignment(int n, int[][] cost) {
        this.n = n;
        this.cost = cost;
        this.bestCost = Integer.MAX_VALUE;
        this.bestAssignment = new int[n];
        this.currentAssignment = new int[n];
        this.assignedJobs = new boolean[n];

        branchAndBound(0, 0);

        // In ket qua
        System.out.println(bestCost);
        for (int i = 0; i < n; i++) {
            System.out.print(bestAssignment[i] + 1); // in theo cong viec tu 1..n
            if (i < n - 1) System.out.print(" ");
        }
        System.out.println();
    }

    private void branchAndBound(int worker, int currentCost) {
        if (worker == n) {
            // Cap nhat loi giai tot nhat
            if (currentCost < bestCost) {
                bestCost = currentCost;
                System.arraycopy(currentAssignment, 0, bestAssignment, 0, n);
            }
            return;
        }

        for (int job = 0; job < n; job++) {
            if (!assignedJobs[job]) {
                // Thu gan cong viec job cho nhan vien worker
                int tempCost = currentCost + cost[worker][job];

                // Tinh can duoi (lower bound)
                assignedJobs[job] = true; // danh dau tam de tinh bound
                int bound = calculateLowerBound(worker + 1, tempCost);
                assignedJobs[job] = false; // tra lai

                if (bound < bestCost) {
                    // Chap nhan nhanh nay
                    assignedJobs[job] = true;
                    currentAssignment[worker] = job;
                    branchAndBound(worker + 1, tempCost);
                    assignedJobs[job] = false; // quay lui
                }
            }
        }
    }

    private int calculateLowerBound(int startWorker, int currentCost) {
        int bound = currentCost;
        // Voi moi nhan vien chua duoc phan cong, tim cong viec re nhat trong so cac cong viec con trong
        for (int i = startWorker; i < n; i++) {
            int minCost = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!assignedJobs[j]) {
                    if (cost[i][j] < minCost) {
                        minCost = cost[i][j];
                    }
                }
            }
            bound += minCost;
        }
        return bound;
    }
}
