package assignment3_recursion;

public class RewardDistribution {
    /**
     * Bai toan chia phan thuong
     * co m phan thuong giong nhau
     * co n hoc sinh xep hang tu 1 -> n
     *
     * Dem so cach phan chia sao cho
     * 1. Hoc sinh hang cao hon nhan khong it hon hoc sinh hang thap hon
     * 2. Phai chia het m phan thuong
     *
     * @param m
     * @param n
     *
     * @return s so cach chia hop le
     */

    // ======================
    /**
     * Giải bằng đệ quy trực tiếp
     * Ý tưởng: Gọi f(n, m, maxVal) = số cách chia m phần thưởng cho n học sinh
     *          với điều kiện học sinh đầu tiên nhận ≤ maxVal
     *
     * Công thức: f(1, m, maxVal) = 1 nếu m ≤ maxVal, ngược lại = 0
     *            f(n, m, maxVal) = Σ f(n-1, m-k, k) với k = 0..min(m, maxVal)
     *
     * Độ phức tạp: O(m^n) - rất chậm, chỉ dùng cho m,n rất nhỏ (m,n ≤ 10)
     */

    public static int countRecursive(int n, int m, int maxValue) {
        if (n == 1) {
            return (m <= maxValue) ? 1 : 0;
        }

        int total = 0;
        int limit = Math.min(m, maxValue);

        // thu tat ca cac gia tri K mma hoc sinh hang 1 co the nhan duoc
        for (int k = 0; k <= limit; k++) {
            total += countRecursive(n - 1, m - k, k);
        }
        return total;
    }

    public static int rewardRecursive(int n, int m) {
        if (m == 0) return 1;  // 0 phần thưởng: 1 cách (tất cả đều 0)
        if (n == 0) return 0;  // 0 học sinh nhưng có phần thưởng → 0 cách
        return countRecursive(n, m, m);
    }
}
