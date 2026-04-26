package assignment5_backtracking_brute_force;

import java.util.*;

public class SubstringSum {

    /**
     * In ra tat ca cac tap con cua tap {1, 2, .., n} co tong bang s
     * In ra cac tap con theo thu tu tang dan
     * Trong moi tap con, cac phan tu duoc in theo thu tu tang dan
     * moi tap con in tren mot dong, cac so cach nhau dung mot dau cach
     * @param n so phan tu cua tap hon
     * @param S tong cac phan tu cua tap hon con
     */

    public void findSubset(int n, int S) {
        List<Integer> current = new ArrayList<>();

    }

    public void backtrack(int start, int n, int rem, List<Integer> current) {
        if (rem == 0) {
            printList(current);
            return;
        }

        for (int i = start; i  < n; i++) {
            if (i > rem) {
                break;
            }

            current.add(i);
            backtrack(i + 1, n, rem - i, current);
            current.remove(current.size() - 1);
        }
    }

    private void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }


}

