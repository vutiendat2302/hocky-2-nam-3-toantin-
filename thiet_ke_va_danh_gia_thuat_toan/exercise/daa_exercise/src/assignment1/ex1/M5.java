package assignment1.ex1;

public class M5 extends TuringMachine {
    private String finalResult;

    public M5(String input) {
        super(input);
        finalResult = "no";  // Mặc định là no
        initializeTransitions();
    }

    private void initializeTransitions() {
        /*
         * M5: Kiểm tra dãy nhị phân có đối xứng không
         * Ví dụ: 010010 => yes, 01100 => no
         *
         * Chiến lược:
         * 1. So sánh ký tự đầu và cuối
         * 2. Nếu giống nhau, xóa cả hai và tiếp tục
         * 3. Nếu khác nhau -> no
         * 4. Nếu hết ký tự hoặc còn 1 ký tự -> yes
         *
         * Các trạng thái:
         * - q0: Tìm ký tự đầu tiên bên trái
         * - q1_0: Đã đọc 0 ở đầu, đi tìm cuối
         * - q1_1: Đã đọc 1 ở đầu, đi tìm cuối
         * - q2_0: So sánh với 0 ở cuối
         * - q2_1: So sánh với 1 ở cuối
         * - q3: Quay lại để tìm cặp tiếp theo
         * - accept: Chuỗi đối xứng
         * - reject: Chuỗi không đối xứng
         */

        // Phase 1: Tìm ký tự đầu tiên bên trái (chưa bị xóa)
        addTransition("q0", "0", "X", "R", "q1_0");   // Gặp 0, đánh dấu X, sang phải
        addTransition("q0", "1", "X", "R", "q1_1");   // Gặp 1, đánh dấu X, sang phải
        addTransition("q0", "X", "X", "R", "q0");     // Bỏ qua X đã đánh dấu
        addTransition("q0", "B", "B", "L", "accept"); // Chỉ còn B -> toàn bộ đã xóa -> đối xứng

        // Phase 2a: Đã đọc 0 ở đầu, đi tìm cuối
        addTransition("q1_0", "0", "0", "R", "q1_0");
        addTransition("q1_0", "1", "1", "R", "q1_0");
        addTransition("q1_0", "X", "X", "R", "q1_0");
        addTransition("q1_0", "B", "B", "L", "q2_0");  // Gặp cuối, lùi lại để so sánh

        // Phase 2b: Đã đọc 1 ở đầu, đi tìm cuối
        addTransition("q1_1", "0", "0", "R", "q1_1");
        addTransition("q1_1", "1", "1", "R", "q1_1");
        addTransition("q1_1", "X", "X", "R", "q1_1");
        addTransition("q1_1", "B", "B", "L", "q2_1");  // Gặp cuối, lùi lại để so sánh

        // Phase 3a: So sánh với 0 ở cuối
        addTransition("q2_0", "0", "X", "L", "q3");    // Gặp 0 -> giống, xóa (đánh dấu X)
        addTransition("q2_0", "1", "1", "L", "reject"); // Gặp 1 -> khác, không đối xứng
        addTransition("q2_0", "X", "X", "L", "q2_0");  // Bỏ qua X

        // Phase 3b: So sánh với 1 ở cuối
        addTransition("q2_1", "1", "X", "L", "q3");    // Gặp 1 -> giống, xóa (đánh dấu X)
        addTransition("q2_1", "0", "0", "L", "reject"); // Gặp 0 -> khác, không đối xứng
        addTransition("q2_1", "X", "X", "L", "q2_1");  // Bỏ qua X

        // Phase 4: Quay lại tìm đầu mới
        addTransition("q3", "0", "0", "L", "q3");
        addTransition("q3", "1", "1", "L", "q3");
        addTransition("q3", "X", "X", "L", "q3");
        addTransition("q3", "B", "B", "R", "q0");     // Về đầu băng, bắt đầu lại

        // Phase 5: Kết thúc
        // accept và reject là trạng thái kết thúc, không có transition
    }

    @Override
    public boolean step() {
        expandTapeIfNeeded();

        String currentChar = tape.get(head);
        String key = state + "," + currentChar;

        if (!transitions.containsKey(key)) {
            return false;
        }

        Transition trans = transitions.get(key);
        tape.set(head, trans.writeChar);

        if (trans.move.equals("R")) {
            head++;
        } else if (trans.move.equals("L")) {
            head--;
        }

        state = trans.nextState;

        // Lưu kết quả nếu đến trạng thái accept hoặc reject
        if (state.equals("accept")) {
            finalResult = "yes";
            return false;
        } else if (state.equals("reject")) {
            finalResult = "no";
            return false;
        }

        return true;
    }

    @Override
    public String run() {
        int steps = 0;
        while (steps < MAX_STEPS) {
            if (!step()) {
                break;
            }
            steps++;
        }

        return finalResult;
    }

    public String runWithDisplay() {
        System.out.println("=== M5: KIỂM TRA ĐỐI XỨNG (PALINDROME) ===");
        String inputStr = String.join("", tape);
        System.out.println("Input: " + inputStr);
        System.out.println("===================================\n");

        int stepCount = 0;
        while (stepCount < MAX_STEPS) {
            System.out.println("Bước " + stepCount + ":");
            display();
            System.out.println();

            if (!step()) {
                System.out.println("Máy dừng tại bước " + stepCount);
                break;
            }
            stepCount++;
        }

        System.out.println("\n📊 KẾT QUẢ:");
        System.out.println("   Input:  " + inputStr);
        System.out.println("   Output: " + finalResult);

        // Giải thích
        System.out.println("\n   Giải thích:");
        if (isPalindrome(inputStr)) {
            System.out.println("      ✓ Đây là chuỗi đối xứng (palindrome)");
            System.out.println("      " + inputStr + " đọc xuôi ngược đều giống nhau");
        } else {
            System.out.println("      ✗ Đây KHÔNG phải chuỗi đối xứng");
            System.out.println("      " + inputStr + " đọc xuôi: " + inputStr);
            System.out.println("      Đọc ngược: " + new StringBuilder(inputStr).reverse().toString());
        }
        System.out.println();

        return finalResult;
    }

    // Hàm helper để kiểm tra palindrome (chỉ để hiển thị giải thích)
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}