package assignment0.ex1;

import java.sql.SQLOutput;
import java.util.*;

public class TestSorting {
    /**
     * Viet mot chuong trinh de danh gia cac thuat toan sap xep
     * a) Với một dãy số nguyên nhỏ được nhập từ bàn phím.
     *
     * b) Sinh N số ngẫu nhiên trong khoảng giá trị [1, 10⁵].
     *
     * c) Cài đặt các thuật toán sắp xếp sau cho các dãy ở trên:
     * Bubble Sort (sắp xếp nổi bọt)
     * Selection Sort (sắp xếp chọn)
     * Insertion Sort (sắp xếp chèn)
     * Merge Sort (sắp xếp trộn)
     * Quick Sort (sắp xếp nhanh)
     * Heap Sort (sắp xếp vun đống)
     * Ngoài ra, có thể cài thêm các thuật toán khác như:
     *
     * Radix Sort
     * Bucket Sort
     * Shell Sort
     *
     * d) Đếm:
     *
     * Số lần so sánh (comparisons)
     * Số lần hoán đổi (swaps)
     *
     * e) Đo thời gian chạy (execution time) của mỗi thuật toán với các giá trị:
     *
     * N = 100
     * N = 1,000
     * N = 10,000
     * N = 100,000
     *
     * Dựa trên kết quả thu được, hãy:
     *
     * Nhận xét
     * Đưa ra kết luận về việc chọn thuật toán phù hợp cho từng giá trị N và các kiểu dữ liệu khác nhau
     */


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Nhap day so nho tu ban phim
        System.out.println("Nhap so luong phan tu cho mang (n):");
        int nSmall = sc.nextInt();

        int[] smallArr = new int[nSmall];
        System.out.println("Nhap cac phan tu cho mang");
        for (int i = 0; i < nSmall; i++) {
            smallArr[i] = sc.nextInt();
        }

        System.out.println("Mang ban dau la: " + Arrays.toString(smallArr));

        int[] testArr;

        // 1. Bubble Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        BubbleSort.sort(testArr);
        System.out.printf("%-18s %s\n", "1. Bubble Sort:", Arrays.toString(testArr));

        // 2. Selection Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        SelectionSort.sort(testArr);
        System.out.printf("%-18s %s\n", "2. Selection Sort:", Arrays.toString(testArr));

        // 3. Insertion Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        InsertionSort.sort(testArr);
        System.out.printf("%-18s %s\n", "3. Insertion Sort:", Arrays.toString(testArr));

        // 4. Merge Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        MergeSort.sort(testArr);
        System.out.printf("%-18s %s\n", "4. Merge Sort:", Arrays.toString(testArr));

        // 5. Quick Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        QuickSort.sort(testArr);
        System.out.printf("%-18s %s\n", "5. Quick Sort:", Arrays.toString(testArr));

        // 6. Heap Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        HeapSort.sort(testArr);
        System.out.printf("%-18s %s\n", "6. Heap Sort:", Arrays.toString(testArr));

        // 7. Shell Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        ShellSort.sort(testArr);
        System.out.printf("%-18s %s\n", "7. Shell Sort:", Arrays.toString(testArr));

        // 8. Radix Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        RadixSort.sort(testArr);
        System.out.printf("%-18s %s\n", "8. Radix Sort:", Arrays.toString(testArr));

        // 9. Bucket Sort
        testArr = Arrays.copyOf(smallArr, smallArr.length);
        BucketSort.sort(testArr);
        System.out.printf("%-18s %s\n", "9. Bucket Sort:", Arrays.toString(testArr));

        System.out.println("\n");

        int[] N_VALUES = {100, 1000, 10000, 100000};
        Map<String, long[]> data = new HashMap<>();
        String[] algorithms = {
                "Merge Sort", "Quick Sort", "Heap Sort",
                "Shell Sort", "Radix Sort", "Bucket Sort"
        };
        for (String algo : algorithms) {
            data.put(algo, new long[N_VALUES.length]);
        }

        int i = 0;
        for (int N : N_VALUES) {

            System.out.println("===============================================================================");
            System.out.println("ĐÁNH GIÁ THUẬT TOÁN VỚI N = " + N);
            System.out.println("===============================================================================");
// Sinh mảng ngẫu nhiên [1, 100,000]
            int[] originalArr = generateRandomArray(N, 1, 100000);
            long start, end;
            int[] temp;

            // 1. Bubble Sort
            if (N <= 10000) {
                temp = Arrays.copyOf(originalArr, N);
                start = System.nanoTime();
                BubbleSort.sort(temp);
                end = System.nanoTime();

                System.out.print(String.format("%-18s", "1. Bubble Sort:"));
                BubbleSort.sort(Arrays.copyOf(originalArr, N)).print();
            } else {
                System.out.println(String.format("%-18s", "1. Bubble Sort:") + "Bỏ qua (quá chậm)");
                // Gán tạm một giá trị 0 hoặc bỏ trống để đồ thị không bị nhiễu
            }

            // 2. Selection Sort
            if (N <= 10000) {
                temp = Arrays.copyOf(originalArr, N);
                start = System.nanoTime();
                SelectionSort.sort(temp);
                end = System.nanoTime();

                System.out.print(String.format("%-18s", "2. Selection Sort:"));
                SelectionSort.sort(Arrays.copyOf(originalArr, N)).print();
            } else {
                System.out.println(String.format("%-18s", "2. Selection Sort:") + "Bỏ qua (quá chậm)");

            }

            // 3. Insertion Sort
            if (N <= 10000) {
                temp = Arrays.copyOf(originalArr, N);
                start = System.nanoTime();
                InsertionSort.sort(temp);
                end = System.nanoTime();

                System.out.print(String.format("%-18s", "3. Insertion Sort:"));
                InsertionSort.sort(Arrays.copyOf(originalArr, N)).print();
            } else {
                System.out.println(String.format("%-18s", "3. Insertion Sort:") + "Bỏ qua (quá chậm)");

            }

            // 4. Merge Sort
            temp = Arrays.copyOf(originalArr, N);
            start = System.nanoTime();
            MergeSort.sort(temp);
            end = System.nanoTime();
            data.get("Merge Sort")[i] = (end - start) / 1_000_000;
            System.out.print(String.format("%-18s", "4. Merge Sort:"));
            MergeSort.sort(Arrays.copyOf(originalArr, N)).print();

            // 5. Quick Sort
            temp = Arrays.copyOf(originalArr, N);
            start = System.nanoTime();
            QuickSort.sort(temp);
            end = System.nanoTime();
            data.get("Quick Sort")[i] = (end - start) / 1_000_000;
            System.out.print(String.format("%-18s", "5. Quick Sort:"));
            QuickSort.sort(Arrays.copyOf(originalArr, N)).print();

            // 6. Heap Sort
            temp = Arrays.copyOf(originalArr, N);
            start = System.nanoTime();
            HeapSort.sort(temp);
            end = System.nanoTime();
            data.get("Heap Sort")[i] = (end - start) / 1_000_000;
            System.out.print(String.format("%-18s", "6. Heap Sort:"));
            HeapSort.sort(Arrays.copyOf(originalArr, N)).print();

            // 7. Shell Sort
            temp = Arrays.copyOf(originalArr, N);
            start = System.nanoTime();
            ShellSort.sort(temp);
            end = System.nanoTime();
            data.get("Shell Sort")[i] = (end - start) / 1_000_000;
            System.out.print(String.format("%-18s", "7. Shell Sort:"));
            ShellSort.sort(Arrays.copyOf(originalArr, N)).print();

            // 8. Radix Sort
            temp = Arrays.copyOf(originalArr, N);
            start = System.nanoTime();
            RadixSort.sort(temp);
            end = System.nanoTime();
            data.get("Radix Sort")[i] = (end - start) / 1_000_000;
            System.out.print(String.format("%-18s", "8. Radix Sort:"));
            RadixSort.sort(Arrays.copyOf(originalArr, N)).print();

            // 9. Bucket Sort
            temp = Arrays.copyOf(originalArr, N);
            start = System.nanoTime();
            BucketSort.sort(temp);
            end = System.nanoTime();
            data.get("Bucket Sort")[i] = (end - start) / 1_000_000;
            System.out.print(String.format("%-18s", "9. Bucket Sort:"));
            BucketSort.sort(Arrays.copyOf(originalArr, N)).print();

            System.out.println();

            // QUAN TRỌNG NHẤT: Tăng i sau khi xong mỗi vòng N
            i++;
        }
        Visualization.showChart(data, N_VALUES);
    }

    public static int[] generateRandomArray(int n, int min, int max) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt((max - min) + 1) + min;
        }
        return arr;
    }
}
