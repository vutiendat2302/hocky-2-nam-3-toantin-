package assignment0.ex1;

public class ShellSort {
    /**
     * Cách hoạt động: Đây là phiên bản nâng cấp của Insertion Sort. Thay vì chỉ so sánh các phần tử kề nhau,
     * Shell Sort so sánh các phần tử cách nhau một khoảng (gap) nhất định.
     * Khoảng cách này giảm dần về 1 (lúc này nó trở thành Insertion Sort).
     * Việc này giúp các phần tử di chuyển về vị trí đúng nhanh hơn.
     *
     * Đặc điểm: Hiệu suất phụ thuộc rất nhiều vào chuỗi khoảng cách (gap sequence) được chọn.
     * Tốt hơn $O(n^2)$ nhưng thường không bằng $O(n \log n)$.
     */

    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        int n = arr.length;

        // Bắt đầu với khoảng cách lớn, sau đó giảm dần
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Thực hiện Insertion Sort cho các phần tử cách nhau một khoảng 'gap'
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;

                res.comparisons++; // Tính lần so sánh đầu tiên của vòng lặp for bên dưới
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                    res.swaps++; // Tính 1 lần gán

                    if (j - gap >= gap) res.comparisons++; // Tính các lần so sánh tiếp theo
                }
                arr[j] = temp;
                res.swaps++; // Tính 1 lần gán
            }
        }

        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }
}
