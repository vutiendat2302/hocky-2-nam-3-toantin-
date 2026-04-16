package assignment0.ex1;

public class QuickSort {
    /**
     * Cách hoạt động: Chọn một phần tử làm "chốt" (pivot). Phân chia mảng sao cho các phần tử nhỏ hơn chốt nằm bên
     * trái, và các phần tử lớn hơn chốt nằm bên phải. Sau đó, đệ quy áp dụng lại quá trình này cho hai nửa trái và phải.
     *
     * Đặc điểm: Rất nhanh trong thực tế nhờ khả năng tối ưu hóa bộ nhớ cache và sắp xếp tại chỗ (in-place).
     * Tuy nhiên, nếu chọn pivot tồi (ví dụ mảng đã sắp xếp sẵn mà chọn pivot ở phần tử đầu/cuối),
     * hiệu suất có thể rớt xuống $O(n^2)$.
     */

    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        quickSortHelper(arr, 0, arr.length - 1, res);
        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }

    private static void quickSortHelper(int[] arr, int low, int high, SortMetrics res) {
        if (low < high) {
            int pi = partition(arr, low, high, res);
            quickSortHelper(arr, low, pi - 1, res);
            quickSortHelper(arr, pi + 1, high, res);
        }
    }

    private static int partition(int[] arr, int low, int high, SortMetrics res) {
        // Đưa các phần tử nhỏ hơn pivot về bên trái, lớn hơn pivot về bên phải
        int randomIndex = low + (int)(Math.random() * (high - low + 1));

        // swap random pivot với high
        int temp1 = arr[randomIndex];
        arr[randomIndex] = arr[high];
        arr[high] = temp1;
        res.swaps++;

        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            res.comparisons++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                res.swaps++;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        res.swaps++;
        return i + 1;
    }
}
