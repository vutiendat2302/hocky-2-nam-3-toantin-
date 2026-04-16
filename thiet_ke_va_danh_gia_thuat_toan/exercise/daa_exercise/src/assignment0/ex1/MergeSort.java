package assignment0.ex1;

public class MergeSort {
    /**
     * Cách hoạt động: Sử dụng chiến lược "Chia để trị". Chia mảng liên tục thành hai nửa cho đến khi mỗi nửa chỉ còn
     * một phần tử. Sau đó, "trộn" (merge) các nửa này lại với nhau theo đúng thứ tự để tạo thành mảng hoàn chỉnh.
     *
     * Đặc điểm: Luôn đảm bảo hiệu suất $O(n \log n)$ trong mọi trường hợp.
     * Tuy nhiên, nhược điểm lớn nhất là cần thêm không gian bộ nhớ phụ $O(n)$ để chứa mảng tạm trong quá trình trộn.
     */

    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1, res);
        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right, SortMetrics res) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid, res);
            mergeSort(arr, temp, mid + 1, right, res);
            merge(arr, temp, left, mid, right, res);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, SortMetrics res) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            res.comparisons++;
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            res.swaps++;
            k++;
        }
        while (i <= mid) {
            arr[k] = temp[i];
            res.swaps++;
            k++;
            i++;
        }

        while (j <= right) {
            arr[k] = temp[j];
            res.swaps++;
            k++;
            j++;
        }
    }
}
