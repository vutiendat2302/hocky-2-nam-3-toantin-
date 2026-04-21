package assignment3_recursion;

import javax.management.StringValueExp;
import java.util.Stack;

public class DecimalToBinary {
    /**
     * Chuyển một số nguyên không âm từ hệ thập phân sang hệ nhị phân (đệ quy)
     * @param n là số nguyên không âm
     * @return biểu diễn nhị phân của n dưới dạng chuỗi
     */
    public String convertRecursive(int n) {
        // TODO
        if (n < 2) {
            return String.valueOf(n);
        } else {
            return convertRecursive(n / 2) +  (n % 2);
        }
    }

    /**
     * Chuyển một số nguyên không âm từ hệ thập phân sang hệ nhị phân (lặp)
     * @param n là số nguyên không âm
     * @return biểu diễn nhị phân của n dưới dạng chuỗi
     */
    public String convertIterative(int n) {
        // TODO

        if (n == 0) {
            return "0";
        }

        Stack<String> stack = new Stack<>();

        while (n != 0) {
            stack.push(String.valueOf(n % 2));
            n = n / 2;
        }
        String result = "";
        while (!stack.empty()) {
            result += stack.pop();
        }
        return result;
    }


}
