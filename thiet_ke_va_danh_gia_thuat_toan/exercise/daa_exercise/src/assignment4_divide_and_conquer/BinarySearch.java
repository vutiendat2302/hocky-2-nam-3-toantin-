package assignment4_divide_and_conquer;

public class BinarySearch {
    /**
     * Cho mang so nguyen da sap xep tang dan va mot so x
     * In ra vi tri cua x trong mang. Neu khong ton tai, in ra -1
     * Dong dau chua n va x, dong 2 chua mang so nguyen
     * @param arr mang so nguyen da sap xep tang dan
     * @param x so can tim vi tri
     */

    public void findIndex(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                System.out.println(mid);
                return;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("-1");
    }

    public int binarySearch(int[] arr, int x, int L, int R) {
        if (L > R) {
            return -1;
        }
        int mid = L + (R - L) / 2;
        if (arr[mid] == x) {
            return mid;
        } else if (arr[mid] < x) {
            return binarySearch(arr, x, mid + 1, R);
        } else {
            return binarySearch(arr, x, L, mid - 1);
        }
    }

}
