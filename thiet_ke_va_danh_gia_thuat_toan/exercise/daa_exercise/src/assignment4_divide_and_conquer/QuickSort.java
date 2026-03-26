package assignment4_divide_and_conquer;

import java.util.Arrays;

public class QuickSort {
    private int[] array;

    /**
     * Ham dung de khoi tao
     * @param array mang dau vao can sap xep
     */

    public QuickSort(int[] array) {
        this.array = array;
    }

    /**
     * Hoan doi han phan tu trong mang
     * @param i chi so phan tu thu nhat
     * @param j chi so phan tu thu hai
     *
     */

    protected void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * phan hoach mang theo pivot co dinh
     * Quy uoc:
     * -pivot luong chon la phan tu cuoi: array[high]
     * yeu cau sau khi phan hoach:
     * - pivot phai nam o dung vi tri trong mang da phan hoach
     * - cac phan tu nho hon pivot nam ben trai pivot
     * - cac phan tu lon hon pivot nam ben phai pivot
     * - khong yeu cau sap xep hoan chinh hai phia
     *
     * @param low chi so bat dau cua doan phan hoahc
     * @param high chi so ket thuc cua doan phan hoach
     * @return chi so vi tri cuoi cung cua pivot
     */

    public int partition(int low, int high) {
        int pivot = array[high];
        return -1;
    }

    /**
     * Thuc hien sap xep mang theo thu tu tang dan bang thuat toan sap xep nhanh
     *
     * @param low chi so dau
     * @param high chi so cuoi
     */

    public void sort(int low, int high) {

    }

    /**
     * Tra ve mang tai mot thoi diem
     * @param mang array
     */
    public int[] getCurrentArray() {
        return array;
    }

    @Override
    public String toString() {
        return "QuickSort Result: \n" +
                "Array: " + Arrays.toString(array);
    }
}
