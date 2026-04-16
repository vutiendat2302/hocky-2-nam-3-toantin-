package assignment1.ex1;

public class M3 extends TuringMachine {

    public M3(String input) {
        super(input);
        initializeTransitions();
    }

    private void initializeTransitions() {
        /*
         * M3: Thay tất cả các số 0 thành 1 và 1 thành 0
         * Ví dụ: 01001 => 10110
         *
         * Chiến lược:
         * 1. Duyệt từ trái sang phải
         * 2. Gặp 0 -> ghi 1, sang phải
         * 3. Gặp 1 -> ghi 0, sang phải
         * 4. Gặp B (cuối băng) -> kết thúc
         */

        // Trạng thái q0: Đọc và đảo bit
        addTransition("q0", "0", "1", "R", "q0");
        addTransition("q0", "1", "0", "R", "q0");
        addTransition("q0", "B", "B", "R", "halt");  // Gặp B thì dừng
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

        // Thu thập kết quả từ băng, loại bỏ ký tự B ở cuối
        StringBuilder result = new StringBuilder();
        for (String s : tape) {
            if (!s.equals(BLANK)) {
                result.append(s);
            } else {
                break;  // Gặp B đầu tiên thì dừng
            }
        }
        return result.toString();
    }

    public String runWithDisplay() {
        System.out.println("=== M3: THAY 0 -> 1 VÀ 1 -> 0 ===");
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

        String result = run();
        System.out.println("\n📊 KẾT QUẢ:");
        System.out.println("   Input:  " + inputStr);
        System.out.println("   Output: " + result);

        // Hiển thị chi tiết từng bước đảo
        System.out.println("\n   Chi tiết đảo bit:");
        for (int i = 0; i < inputStr.length(); i++) {
            char original = inputStr.charAt(i);
            char flipped = (original == '0') ? '1' : '0';
            System.out.println("      Vị trí " + i + ": " + original + " → " + flipped);
        }
        System.out.println();

        return result;
    }
}