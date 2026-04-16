package assignment1.ex1;

public class M2 extends TuringMachine {

    public M2(String input) {
        super(input);
        initializeTransitions();
    }

    private void initializeTransitions() {
        // q0: Di chuyển sang phải đến cuối băng
        addTransition("q0", "0", "0", "R", "q0");
        addTransition("q0", "1", "1", "R", "q0");
        addTransition("q0", "B", "B", "L", "q1");

        // q1: Lùi và tìm bit 1 đầu tiên
        addTransition("q1", "0", "1", "L", "q1");
        addTransition("q1", "1", "0", "L", "q2");
        addTransition("q1", "B", "0", "R", "halt");

        // q2: Tiếp tục lùi về đầu băng
        addTransition("q2", "0", "0", "L", "q2");
        addTransition("q2", "1", "1", "L", "q2");
        addTransition("q2", "B", "B", "R", "halt");
    }

    private String removeLeadingZeros(String result) {
        if (result == null || result.isEmpty()) {
            return "0";
        }
        if (result.matches("0+")) {
            return "0";
        }
        return result.replaceFirst("^0+(?!$)", "");
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

        StringBuilder resultBuilder = new StringBuilder();
        for (String s : tape) {
            if (!s.equals(BLANK)) {
                resultBuilder.append(s);
            }
        }
        String rawResult = resultBuilder.length() == 0 ? "0" : resultBuilder.toString();
        return removeLeadingZeros(rawResult);
    }

    public String runWithDisplay() {
        System.out.println("=== M2: TRỪ 1 CHO SỐ NHỊ PHÂN ===");
        System.out.println("Input: " + String.join("", tape));
        System.out.println("===================================\n");

        int stepCount = 0;
        while (stepCount < MAX_STEPS) {
            System.out.println("Bước " + stepCount + ":");
            display();
            System.out.println();

            if (!step()) {
                break;
            }
            stepCount++;
        }

        String result = run();
        System.out.println("Kết quả: " + result + "\n");
        return result;
    }
}
