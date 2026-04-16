package assignment0.ex1;

public class HeapSort {
    /**
     * Cách hoạt động: Biểu diễn mảng dưới dạng cấu trúc dữ liệu cây nhị phân (Max-Heap).
     * Đưa phần tử lớn nhất (ở gốc cây) về cuối mảng, sau đó tái cấu trúc lại
     * Heap cho các phần tử còn lại và lặp lại quá trình.
     *
     * Đặc điểm: Không cần bộ nhớ phụ như Merge Sort và không bị rơi vào trường hợp xấu $O(n^2)$ như Quick Sort.
     * Tuy nhiên, trong thực tế thường chạy chậm hơn Quick Sort do cách truy cập bộ nhớ
     * nhảy cóc (kém thân thiện với cache).
     */

    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i, res);
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            res.swaps++;
            heapify(arr, i, 0, res);
        }
        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }

    private static void heapify(int[] arr, int n, int i, SortMetrics res) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n) {
            res.comparisons++;
            if (arr[l] > arr[largest]) largest = l;
        }
        if (r < n) {
            res.comparisons++;
            if (arr[r] > arr[largest]) largest = r;
        }

        res.comparisons++;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            res.swaps++;
            heapify(arr, n, largest, res);
        }
    }
}
