package assignment4_divide_and_conquer;

import java.util.Arrays;

public class MergeSort {
    private int[] array;

    /**
     * Ham dung de khoi tao
     * @param array mang dau vao can sap xep
     *
     */

    public MergeSort(int[] array) {
        this.array = array;
    }

    /**
     * Tron (merge) hai nua da sap xep cua mang
     * Quy uoc:
     * - Doan [left, mid] da duoc sap xep
     * - Doan [mid + 1, right] da duoc sap xep
     * yeu cau sau khi tron:
     * - Doan [left, right] duoc sap xep hoan chinh
     *
     * @param left chi so bat dau
     * @param mid chi so giua
     * @param right chi so ket thuc
     *
     */

    public void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Sao chép dữ liệu vào mảng tạm
        for (int i = 0; i < n1; i++)
            leftArr[i] = array[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            array[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArr[j];
            j++;
            k++;
        }
    }

    /**
     * thuc hien sap xep mang theo thu tu tang dan bang thuat tuan sap xep tron
     * @param left chi so dau
     * @param right chi so cuoi
     */

    public void sort(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            sort(left, mid);
            sort(mid + 1, right);

            merge(left, mid, right);
        }


    }

    /**
     * Tra mang tai mot thoi diem
     * @return mang array hien tai
     */

    public int[] getCurrentArray() {
        return array;
    }

    @Override
    public String toString() {
        return "MergeSort Result: \n" +
                "Array: " + Arrays.toString(array);
    }
}
