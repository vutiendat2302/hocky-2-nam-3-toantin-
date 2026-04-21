package assignment4_divide_and_conquer;

public class EggDrop {
    /**
     * Ta co toa nha n tang va giỏ trứng có độ cứng là k (k <= n)
     * tha qua trung tu tang k tro xuong thi se khong vo trung
     * tha qua trung tu tang k + 1 tro len thi trung se vo
     * tim va in ra so k voi it lan thu tha trung nhat
     *
     * @param n so tang cua toa nha
     * @param k do cung cua trung
     */

    public void solveEggDrop(int n, int k) {
        int left = 1;
        int right = n;
        int ans = 0; // ket qua
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= k) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private int solveRecursive(int left, int right, int k) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (mid == k) {
            return mid;
        } else if (mid < k) {
            return solveRecursive(mid + 1, right, k);
        } else {
            return solveRecursive(left, mid - 1, k);
        }
    }
}
