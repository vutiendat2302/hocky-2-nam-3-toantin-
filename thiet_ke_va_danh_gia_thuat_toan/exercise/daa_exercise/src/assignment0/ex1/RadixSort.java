package assignment0.ex1;

public class RadixSort {
    /**
     * Cách hoạt động: Không dùng phép so sánh trực tiếp giữa các số. Thuật toán phân loại các con số dựa trên từng chữ
     * số của chúng, bắt đầu từ chữ số hàng đơn vị, rồi đến hàng chục, hàng trăm, v.v.
     * (thường sử dụng Counting Sort làm thuật toán ổn định hỗ trợ bên trong).
     *
     * Đặc điểm: Rất nhanh đối với dữ liệu là các số nguyên có độ dài hữu hạn.
     * Độ phức tạp là $O(nk)$ với $k$ là độ dài của số lớn nhất.
     */

    public static SortResult sort(int[] arr) {
        SortMetrics res = new SortMetrics();
        long startTime = System.nanoTime();
        int n = arr.length;
        if (n == 0) return res;

        // Tìm phần tử lớn nhất để biết số lượng chữ số
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            res.comparisons++;
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Thực hiện Counting Sort cho từng chữ số (exp = 1, 10, 100...)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, n, exp, res);
        }

        res.times = (System.nanoTime() - startTime) / 1_000_000.0;
        return res;
    }

    // Hàm đếm và phân loại phụ trợ
    private static void countSort(int[] arr, int n, int exp, SortMetrics res) {
        int[] output = new int[n];
        int[] count = new int[10];

        // Lưu số lần xuất hiện của các chữ số
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Thay đổi count[i] sao cho nó chứa vị trí thực tế của chữ số này trong output
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Xây dựng mảng output
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            res.swaps++; // Tính là thao tác gán/phân bổ dữ liệu
            count[(arr[i] / exp) % 10]--;
        }

        // Sao chép mảng output lại vào arr[]
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
            res.swaps++; // Tính thao tác chép đè vào mảng gốc
        }
    }
}
