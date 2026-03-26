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

    }

    /**
     * thuc hien sap xep mang theo thu tu tang dan bang thuat tuan sap xep tron
     * @param left chi so dau
     * @param right chi so cuoi
     */

    public void sort(int left, int right) {

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
