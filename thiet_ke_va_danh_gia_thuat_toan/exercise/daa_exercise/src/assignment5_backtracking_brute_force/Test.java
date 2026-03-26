package assignment5_backtracking_brute_force;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class BinaryStringGeneratorTest {

    private BinaryStringGenerator gen;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        gen = new BinaryStringGenerator();
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    private String[] getLines() {
        return out.toString().trim().split(System.lineSeparator());
    }

    @Test void testN1_count()  { gen.printBinaryString(1); assertEquals(2, getLines().length); }
    @Test void testN1_order()  { gen.printBinaryString(1); assertArrayEquals(new String[]{"0","1"}, getLines()); }
    @Test void testN2_count()  { gen.printBinaryString(2); assertEquals(4, getLines().length); }
    @Test void testN2_order()  { gen.printBinaryString(2); assertArrayEquals(new String[]{"00","01","10","11"}, getLines()); }
    @Test void testN3_count()  { gen.printBinaryString(3); assertEquals(8, getLines().length); }
    @Test void testN3_firstAndLast() {
        gen.printBinaryString(3);
        String[] lines = getLines();
        assertEquals("000", lines[0]);
        assertEquals("111", lines[lines.length - 1]);
    }
    @Test void testAllUnique() {
        gen.printBinaryString(3);
        assertEquals(8, Arrays.stream(getLines()).distinct().count());
    }
    @Test void testCorrectLength() {
        gen.printBinaryString(4);
        for (String line : getLines()) assertEquals(4, line.length());
    }
    @Test void testOnlyBinaryChars() {
        gen.printBinaryString(4);
        for (String line : getLines()) assertTrue(line.matches("[01]+"));
    }
}