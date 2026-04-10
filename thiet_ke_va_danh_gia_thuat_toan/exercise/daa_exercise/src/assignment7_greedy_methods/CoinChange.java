package assignment7_greedy_methods;

import java.util.Arrays;

public class CoinChange {
    /**
     * tim so luong dong xu it nhat de tao thanh so tien amount
     * bang phuong phap tham lam
     *
     * @param coins mang chua cac menh gia (so nguyen duong)
     * @param amount so tien can doi
     * @return so dong it nhat, hoac -1 neu khong the doi
     */

    public int coinChange(int[] coins, int amount) {
        // todo
        Arrays.sort(coins);
        int count_check = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            int count = amount / coins[i];
            count_check += count;

            amount = amount % coins[i];
        }

        if (amount != 0) {
            return -1;
        }
        return count_check;
    }


    public static class TestCoinChange {
        public static void main(String[] args) {
            CoinChange solver = new CoinChange();

            // Test case 1: Hệ thống tiền tệ chuẩn (Tiền Việt Nam/Đô la)
            // 18k = 10k + 5k + 2k + 1k => 4 đồng
            int[] coins1 = {1, 2, 5, 10};
            System.out.println("Test 1 (Standard): " + (solver.coinChange(coins1, 18) == 4 ? "PASSED" : "FAILED"));

            // Test case 2: Không thể đổi được tiền
            // Cần 7đ nhưng chỉ có tờ 5đ và 10đ
            int[] coins2 = {5, 10};
            System.out.println("Test 2 (Impossible): " + (solver.coinChange(coins2, 7) == -1 ? "PASSED" : "FAILED"));

            // Test case 3: Mảng chưa sắp xếp
            // Phải đảm bảo hàm của mày có Arrays.sort()
            int[] coins3 = {5, 1, 10, 2};
            System.out.println("Test 3 (Unsorted): " + (solver.coinChange(coins3, 13) == 3 ? "PASSED" : "FAILED"));

            // Test case 4: Trường hợp THAM LAM BỊ SAI (Cảnh báo!)
            // Đổi 6đ với {1, 3, 4}.
            // Tham lam: 4 + 1 + 1 (3 đồng)
            // Tối ưu thực tế: 3 + 3 (2 đồng)
            int[] coins4 = {1, 3, 4};
            int result4 = solver.coinChange(coins4, 6);
            System.out.println("Test 4 (Greedy Limitation): Result = " + result4 + " (Greedy ra 3, nhưng Dynamic Programming sẽ ra 2)");
        }
    }

}
