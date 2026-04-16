package assignment0.ex1;

public class BubbleSort {
    /**
     * Sắp xếp nổi bọt
     * Cách hoạt động: lặp qua nhiều lần, liên tục so sánh các cặp phần tử liền kề và hoán đổi chúng neếu chúng sai thứ tự.
     * Đẩy phần tử lớn nhất về cuối mảng sau mỗi vòng lặp
     */

    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                res.comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    res.swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }
}
