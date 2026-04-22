package assignment4_divide_and_conquer;

public class Bisection {

    public static double f(double x) {
        return x * Math.cos(x) - Math.exp(-x) - x*x/8 + 2;
    }

    /**
     * Giai phuong trinh f(x) = 0 bang phuong phpa chia doi
     *
     * Y tuong:
     * Gia su ham f(x) lien tuc tren doan [a,b] va f(a) * f(b) < 0
     * Khi do phuong trinh f(x) = 0 co it nhat mot nghiem trong (a, b)
     *
     * Thuat toan:
     * 1. Kiem tra dieu kien f(a) * f(b) < 0
     * Neu khong thoa man, in ra "No Solution"
     * 2. Lap cho den khi |b-a| < eps:
     * - tinh c = (a+b) / 2
     * - Neu f(a) * f(c) <= 0 thi nghiem nam trong [a,c] dat b = c
     * - Nguoc lai nghiem nam trong[c,b] dat a = c
     * 3. Khi dung, nghiem gan dung la (a + b) / 2
     * Ket qua lam trong den chu so so phan thap phan thu 6.
     *
     * @param a dau mut trai cua khoang
     * @param b dau mut phai cua khoang
     * @param eps sai so cho phep
     *
     */

    public void solve(double a, double b, double eps) {
        if (f(a) * f(b) >= 0) {
            System.out.println("No Solution");
            return;
        }

        double c;
        while (Math.abs(b - a) >= eps) {
            c = (a + b) / 2;

            if (Math.abs(f(c)) < 1e-12) {
                System.out.printf("%.6f\n", c);
                return;
            }

            if (f(a) * f(c) <= 0) {
                b = c;
            } else {
                a = c;
            }
        }

        double result = (a + b) / 2;
        System.out.printf("%.6f\n", result);
    }

    public void solveDivide(double a, double b, double eps) {
        if (f(a) * f(b) >= 0) {
            System.out.println("No Solution");
            return;
        }

        double root = bisectionRecursive(a, b, eps);
        System.out.printf("%.6f\n", root);
    }

    public double bisectionRecursive(double a, double b, double eps) {
        double c = (a + b) / 2;
        if ((b - a) < eps || Math.abs(f(c)) < 1e-12) {
            return c;
        }

        if (f(a) * f(c) < 0) {
            return bisectionRecursive(a, c, eps); // nghiệm trong [a, c]
        } else {
            return bisectionRecursive(c, b, eps); // nghiệm trong [c, b]
        }
    }
}
