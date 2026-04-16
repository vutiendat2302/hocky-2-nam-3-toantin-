package assignment1.ex1;

public class MainM2 {
    public static void main(String[] args) {
        System.out.println("=== TEST MÁY TURING M2 - PHÉP TRỪ 1 ===");
        System.out.println("=========================================\n");

        // Mảng các test case: {input, expected}
        String[][] tests = {
                {"1", "0"},      // 1 - 1 = 0
                {"10", "1"},     // 2 - 1 = 1
                {"11", "10"},    // 3 - 1 = 2
                {"100", "11"},   // 4 - 1 = 3
                {"101", "100"},  // 5 - 1 = 4
                {"110", "101"},  // 6 - 1 = 5
                {"111", "110"},  // 7 - 1 = 6
                {"1000", "111"}, // 8 - 1 = 7
                {"1010", "1001"},// 10 - 1 = 9
                {"1111", "1110"},// 15 - 1 = 14
                {"10000", "1111"}// 16 - 1 = 15
        };

        boolean allPass = true;

        System.out.println("Kiểm tra nhanh các test case:");
        System.out.println("------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %s\n", "Input", "Expected", "Result", "Status");
        System.out.println("------------------------------------------------");

        for (String[] test : tests) {
            String input = test[0];
            String expected = test[1];

            M2 tm = new M2(input);
            String result = tm.run();

            boolean passed = result.equals(expected);
            allPass = allPass && passed;

            System.out.printf("%-10s %-10s %-10s %s\n",
                    input, expected, result, passed ? "✓ PASS" : "✗ FAIL");
        }

        System.out.println("------------------------------------------------");
        System.out.println("\n" + (allPass ? "✅ Tất cả test đều PASS!" : "❌ Có test FAILED!"));

        // Mô phỏng chi tiết một vài trường hợp
        System.out.println("\n\n=== MÔ PHỎNG CHI TIẾT CÁC TRƯỜNG HỢP ===");

        String[] detailedTests = {"1000", "1010", "1111"};
        for (String input : detailedTests) {
            System.out.println("\n" + "=".repeat(50));
            M2 tm = new M2(input);
            tm.runWithDisplay();
            System.out.println("=".repeat(50));
        }

        // Test trường hợp đặc biệt: số 0
        System.out.println("\n\n=== TRƯỜNG HỢP ĐẶC BIỆT: SỐ 0 ===");
        M2 tmZero = new M2("0");
        String resultZero = tmZero.runWithDisplay();
        System.out.println("0 - 1 = " + resultZero + " (theo quy ước)");
    }
}
