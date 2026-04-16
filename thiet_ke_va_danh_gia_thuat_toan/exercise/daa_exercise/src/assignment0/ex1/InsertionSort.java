package assignment0.ex1;

public class InsertionSort {
    /**
     * Cách hoạt động: Xây dựng mảng đã sắp xếp bằng cách duyệt qua từng phần tử một.
     * Lấy phần tử hiện tại và "chèn" nó vào đúng vị trí của nó trong danh sách các phần tử đã duyệt trước đó
     * (tương tự như cách bạn sắp xếp các lá bài trên tay).
     *
     * Đặc điểm: Hoạt động cực kỳ hiệu quả đối với các mảng nhỏ hoặc mảng đã gần được sắp xếp.
     * Thường được dùng làm thuật toán hỗ trợ bên trong các thuật toán phức tạp hơn (như Timsort).
     */

    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        int n = arr.length;

        for (int i = 1; i < n; i++)  {
            int key = arr[i];
            int j = i - 1;

            res.comparisons++;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                res.swaps++;
                j = j - 1;
                if (j >= 0) {
                    res.comparisons++;
                }
            }
            arr[j + 1] = key;
            res.swaps++;
        }
        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }
}
