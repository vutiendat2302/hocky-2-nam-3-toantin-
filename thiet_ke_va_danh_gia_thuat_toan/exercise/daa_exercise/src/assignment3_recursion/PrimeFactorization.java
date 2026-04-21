package assignment3_recursion;
import java.util.LinkedList;
import java.util.List;
public class PrimeFactorization {
    /**
     * Phân tích một số nguyên dương thành các thừa số nguyên tố (đệ quy)
     *
     * @param n là số nguyên dương lớn hơn 1
     * @return danh sách các thừa số nguyên tố của n
     */
    public List<Integer> factorRecursive(int n){
        // TODO
        List<Integer> result = new LinkedList<>();
        factorHelper(n, 2, result);
        return result;
    }

    public void factorHelper(int n, int i, List<Integer> list) {
        if (n < 2) {
            return;
        }
        if (n % i == 0) {
            list.add(i);
            factorHelper(n / i, i, list);
        } else {
            factorHelper(n, i + 1, list);
        }
    }


    /**
     * Phân tích một số nguyên dương thành các thừa số nguyên tố (lặp)
     *
     * @param n là số nguyên dương lớn hơn 1
     * @return danh sách các thừa số nguyên tố của n
     */
    public List<Integer> factorIterative(int n) {
        // TODO
        List<Integer> result = new LinkedList<>();

        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                result.add(i);
                n = n / i;
            }
        }

        if (n > 1) {
            result.add(n);
        }
        return result;
    }
}
