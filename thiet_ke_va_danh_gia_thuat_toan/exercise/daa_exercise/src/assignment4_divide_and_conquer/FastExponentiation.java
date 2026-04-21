package assignment4_divide_and_conquer;

public class FastExponentiation {

    /**
     * tinh a^n bang phuong phap tinh nhanh
     * y tuong: giam n xuong n/2 moi buoc
     * in ra dap an
     *
     * @param a so a
     * @param n so mu
     *
     */

    public void cal(int a, int n) {
        long result = fastPow(a, n);
        System.out.println(result);
    }

    public long fastPow(int a, int n) {
        if (n == 0) return 1;
        if (n == 1) return a;
        long half = fastPow(a, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * a;
        }
    }
}
