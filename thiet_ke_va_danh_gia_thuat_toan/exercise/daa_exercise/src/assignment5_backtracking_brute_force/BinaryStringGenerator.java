package assignment5_backtracking_brute_force;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryStringGenerator {

    /**
     * In ra tat ca chuoi nhi phan do dai n theo thu tu tu be den lon
     * @param n do dai cua chuoi nhi phan
     */

    public void printBinaryString(int n) {
        backtracking("", n);
    }

    public void backtracking(String s, int n) {
        if (s.length() == n) {
            System.out.println(s);
            return;
        }

        backtracking(s + "0", n);
        backtracking(s + "1", n);
    }
}
