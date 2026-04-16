package assignment0.ex1;

import java.util.ArrayList;

public class BucketSort {
    /**
     * Cách hoạt động: Chia khoảng giá trị của dữ liệu thành nhiều "xô" (bucket) bằng nhau.
     * Phân phối các phần tử vào xô tương ứng. Sau đó, sắp xếp từng xô (thường dùng Insertion Sort)
     * và nối các xô lại với nhau.
     *
     * Đặc điểm: Cực kỳ hiệu quả ($O(n)$) nếu dữ liệu đầu vào được phân bố đều (uniform distribution).
     * Nếu dữ liệu tập trung vào một vài xô, hiệu suất sẽ giảm mạnh.
     */
    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        int n = arr.length;
        if (n <= 0) return res;

        // Tìm Min, Max để chia độ rộng của xô
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < n; i++) {
            res.comparisons += 2;
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }

        // Tạo số lượng xô (thường lấy bằng căn bậc 2 của n, hoặc bằng n)
        int bucketCount = (int) Math.sqrt(n);
        if (bucketCount == 0) bucketCount = 1;

        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Đưa các phần tử vào xô tương ứng
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) ((double) (arr[i] - min) / (max - min + 1) * bucketCount);
            if (bucketIndex >= bucketCount) bucketIndex = bucketCount - 1;
            buckets.get(bucketIndex).add(arr[i]);
            res.swaps++; // Tính là 1 thao tác gán vào xô
        }

        // Sắp xếp từng xô và gộp lại
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            ArrayList<Integer> bucket = buckets.get(i);

            // Sắp xếp xô bằng Insertion Sort (Có theo dõi so sánh và hoán đổi)
            for (int j = 1; j < bucket.size(); ++j) {
                int key = bucket.get(j);
                int k = j - 1;
                res.comparisons++;
                while (k >= 0 && bucket.get(k) > key) {
                    bucket.set(k + 1, bucket.get(k));
                    res.swaps++;
                    k--;
                    if (k >= 0) res.comparisons++;
                }
                bucket.set(k + 1, key);
                res.swaps++;
            }

            // Gộp lại vào mảng gốc
            for (int val : bucket) {
                arr[index++] = val;
                res.swaps++; // Thao tác gán trả lại mảng gốc
            }
        }

        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }
}
