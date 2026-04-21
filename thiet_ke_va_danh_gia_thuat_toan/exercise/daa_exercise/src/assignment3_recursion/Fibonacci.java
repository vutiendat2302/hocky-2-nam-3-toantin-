package assignment3_recursion;

public class Fibonacci {
    /*
    * Tính số Fibonacci thứ n bằng phương pháp đệ quy
    *
    * @param n : số tự nhiên chỉ số của số Fibonacci cần tinh s
    * @return giá tri Fibonacci thứ n
    * */

    public int fibonacciRecursive(int n) {
        // TODO
        if (n <= 1) {
            return n; // n=0 trả về 0, n=1 trả về 1
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * Tính số Fibonacci thứ n bằng phương pháp không đệ quy
     *
     * @param n số tự nhiên chỉ số của số Fibonacci cần tính
     * @return giá trị Fibonacci thứ n
     */
    public int fibonacciIterative(int n) {
        // TODO
        if (n <= 1) return n;
        int b = 1;
        int a = 0;
        for (int i = 2; i <= n; i++) {
            int temp = b;
            b += a;
            a = temp;
        }
        return b;
    }
}
