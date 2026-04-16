package assignment1.ex1;

public class TestM3M5 {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("TEST M3 VÀ M5");
        System.out.println("=========================================\n");

        // Test M3
        System.out.println("--- M3: Đảo bit ---");
        String[] m3Tests = {
                "0",        // -> 1
                "1",        // -> 0
                "01",       // -> 10
                "10",       // -> 01
                "01001",    // -> 10110 (ví dụ trong đề)
                "111000",   // -> 000111
                "101010"    // -> 010101
        };

        for (String test : m3Tests) {
            M3 m3 = new M3(test);
            String result = m3.run();
            System.out.printf("%-10s -> %-10s %s\n", test, result,
                    result.equals(flipBits(test)) ? "✓" : "✗");
        }

        // Test M5
        System.out.println("\n--- M5: Kiểm tra đối xứng ---");
        String[][] m5Tests = {
                {"0", "yes"},
                {"1", "yes"},
                {"00", "yes"},
                {"11", "yes"},
                {"01", "no"},
                {"10", "no"},
                {"010", "yes"},
                {"101", "yes"},
                {"0110", "yes"},
                {"01010", "yes"},
                {"01100", "no"},      // Ví dụ trong đề
                {"010010", "yes"},    // Ví dụ trong đề
                {"1001", "yes"},
                {"1011", "no"}
        };

        for (String[] test : m5Tests) {
            String input = test[0];
            String expected = test[1];
            M5 m5 = new M5(input);
            String result = m5.run();
            System.out.printf("%-10s -> %-6s (Expected: %-3s) %s\n",
                    input, result, expected, result.equals(expected) ? "✓" : "✗");
        }

        // Mô phỏng chi tiết M3
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MÔ PHỎNG CHI TIẾT M3");
        System.out.println("=".repeat(60));
        M3 m3Detail = new M3("01001");
        m3Detail.runWithDisplay();

        // Mô phỏng chi tiết M5
        System.out.println("=".repeat(60));
        System.out.println("MÔ PHỎNG CHI TIẾT M5 - Test 1");
        System.out.println("=".repeat(60));
        M5 m5Detail1 = new M5("010010");
        m5Detail1.runWithDisplay();

        System.out.println("=".repeat(60));
        System.out.println("MÔ PHỎNG CHI TIẾT M5 - Test 2");
        System.out.println("=".repeat(60));
        M5 m5Detail2 = new M5("01100");
        m5Detail2.runWithDisplay();
    }

    private static String flipBits(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            result.append(c == '0' ? '1' : '0');
        }
        return result.toString();
    }
}