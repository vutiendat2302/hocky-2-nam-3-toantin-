package assignment0.ex1;

public class SelectionSort {
    /**
     * Cách hoạt động: Chia mảng thành hai phần: phần đã sắp xếp và phần chưa sắp xếp.
     * Thuật toán tìm phần tử nhỏ nhất trong phần chưa sắp xếp và hoán đổi nó với phần tử đầu tiên của phần chưa sắp xếp,
     * từ đó mở rộng phần đã sắp xếp thêm một phần tử.
     *
     * Đặc điểm: Số lượng phép hoán đổi ít hơn Bubble Sort (tối đa $n-1$ lần),
     * nhưng vẫn chậm vì luôn phải quét toàn bộ phần chưa sắp xếp để tìm giá trị nhỏ nhất.
     */

    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                res.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                res.swaps++;
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }
}
