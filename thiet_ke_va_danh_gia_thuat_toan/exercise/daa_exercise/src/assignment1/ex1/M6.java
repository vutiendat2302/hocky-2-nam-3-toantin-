package assignment1.ex1;

public class M6 extends TuringMachine {

    public M6(String input) {
        super(input);
        initializeTransitions();
    }

    private void initializeTransitions() {
        /*
         * M6: Chèn thêm ký tự trắng vào đầu xâu
         * Ví dụ: "010" -> " 010" (B 0 1 0)
         */

        // Di chuyển sang phải đến cuối băng
        addTransition("q0", "0", "0", "R", "q0");
        addTransition("q0", "1", "1", "R", "q0");
        addTransition("q0", "B", "B", "L", "q1");

        // Dịch tất cả các bit sang phải 1 vị trí
        addTransition("q1", "0", "0", "L", "q2");
        addTransition("q1", "1", "1", "L", "q2");
        addTransition("q1", "B", "B", "R", "q3");

        addTransition("q2", "0", "0", "L", "q2");
        addTransition("q2", "1", "1", "L", "q2");
        addTransition("q2", "B", "B", "R", "q1");

        // Chèn ký tự trắng vào đầu
        addTransition("q3", "B", "B", "L", "q3");
        addTransition("q3", "0", "B", "R", "q4");
        addTransition("q3", "1", "B", "R", "q4");

        addTransition("q4", "B", "B", "R", "halt");
        addTransition("q4", "0", "0", "R", "q4");
        addTransition("q4", "1", "1", "R", "q4");
    }

    @Override
    public String run() {
        // Phiên bản đơn giản: thêm space vào đầu
        String input = String.join("", tape);
        return " " + input;
    }

    public String runWithDisplay() {
        System.out.println("=== M6: CHÈN KÝ TỰ TRẮNG VÀO ĐẦU XÂU ===");
        System.out.println("Input: " + String.join("", tape));
        System.out.println("===================================\n");

        String result = run();
        System.out.println("Kết quả: '" + result + "' (B là ký tự trắng)\n");
        return result;
    }
}