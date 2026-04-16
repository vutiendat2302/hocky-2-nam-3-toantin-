package assignment1.ex1;

public class M1 extends TuringMachine {

    public M1(String input) {
        super(input);
        initializeTransitions();
    }

    private void initializeTransitions() {
        // q0: Di chuyển sang phải đến cuối băng
        addTransition("q0", "0", "0", "R", "q0");
        addTransition("q0", "1", "1", "R", "q0");
        addTransition("q0", "B", "B", "L", "q1");

        // q1: Lùi và thực hiện phép cộng
        addTransition("q1", "0", "1", "L", "halt");
        addTransition("q1", "1", "0", "L", "q1");
        addTransition("q1", "B", "1", "L", "halt");
    }

    public String runWithDisplay() {
        System.out.println("=== M1: CỘNG 1 CHO SỐ NHỊ PHÂN ===");
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
