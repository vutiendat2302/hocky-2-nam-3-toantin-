package assignment1.ex1;

public class MainM1 {
    public static void main(String[] args) {
        System.out.println("=== TEST MÁY TURING M1 ===\n");

        // Mảng các test case
        String[][] tests = {
                {"0", "1"},
                {"1", "10"},
                {"10", "11"},
                {"11", "100"},
                {"101", "110"},
                {"110", "111"},
                {"111", "1000"},
                {"1011", "1100"},
                {"1111", "10000"},
                {"1000", "1001"}
        };

        boolean allPass = true;

        for (String[] test : tests) {
            String input = test[0];
            String expected = test[1];

            M1 tm = new M1(input);
            String result = tm.run();

            boolean passed = result.equals(expected);
            allPass = allPass && passed;

            System.out.printf("Input: %-8s Expected: %-8s Result: %-8s %s\n",
                    input, expected, result, passed ? "✓" : "✗");
        }

        System.out.println("\n" + (allPass ? "Tất cả test đều PASS!" : "Có test FAILED!"));

        // Test chi tiết một trường hợp
        System.out.println("\n\n=== MÔ PHỎNG CHI TIẾT VÍ DỤ ===");
        M1 tm = new M1("1011");  // 11 + 1 = 12 (1100)
        tm.runWithDisplay();

    }
}
