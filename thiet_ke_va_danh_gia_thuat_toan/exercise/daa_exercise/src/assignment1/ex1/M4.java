package assignment1.ex1;

public class M4 extends TuringMachine {

    public M4(String input) {
        super(input);
        initializeTransitions();
    }

    private void initializeTransitions() {
        /*
         * M4: Loại bỏ các chữ số 0 trong dãy nhị phân
         * Ví dụ: 01001 -> 11
         *
         * Chiến lược:
         * 1. Duyệt từ trái sang phải
         * 2. Gặp 0 -> xóa (ghi B)
         * 3. Gặp 1 -> giữ nguyên
         * 4. Sau đó dồn các số 1 lại
         */

        // Phase 1: Đánh dấu các số 0 để xóa
        addTransition("q0", "0", "B", "R", "q0");  // Xóa 0
        addTransition("q0", "1", "1", "R", "q1");  // Gặp 1, chuyển sang q1
        addTransition("q0", "B", "B", "L", "q2");  // Kết thúc, bắt đầu dồn

        // Phase 2: Di chuyển các số 1 sang trái
        addTransition("q1", "0", "B", "R", "q1");  // Xóa 0
        addTransition("q1", "1", "1", "R", "q1");  // Giữ 1
        addTransition("q1", "B", "B", "L", "q2");  // Kết thúc, bắt đầu dồn

        // Phase 3: Dồn các số 1 sang trái
        addTransition("q2", "B", "B", "L", "q2");   // Lùi tìm số 1
        addTransition("q2", "1", "B", "L", "q3");   // Tìm thấy 1, chuyển sang q3
        addTransition("q2", "0", "B", "L", "q2");   // Bỏ qua 0

        // Phase 4: Di chuyển 1 về đầu
        addTransition("q3", "B", "1", "R", "q4");   // Viết 1 vào vị trí trống
        addTransition("q3", "0", "1", "R", "q4");
        addTransition("q3", "1", "1", "R", "q4");

        // Phase 5: Quay lại tìm tiếp
        addTransition("q4", "B", "B", "L", "q2");   // Tiếp tục
        addTransition("q4", "0", "0", "R", "q4");
        addTransition("q4", "1", "1", "R", "q4");
    }

    @Override
    public String run() {
        // Phiên bản đơn giản hơn
        StringBuilder result = new StringBuilder();
        String input = String.join("", tape);
        for (char c : input.toCharArray()) {
            if (c == '1') {
                result.append('1');
            }
        }
        return result.toString();
    }

    public String runWithDisplay() {
        System.out.println("=== M4: LOẠI BỎ SỐ 0 ===");
        System.out.println("Input: " + String.join("", tape));
        System.out.println("===================================\n");

        String result = run();
        System.out.println("Kết quả: " + result + "\n");
        return result;
    }
}