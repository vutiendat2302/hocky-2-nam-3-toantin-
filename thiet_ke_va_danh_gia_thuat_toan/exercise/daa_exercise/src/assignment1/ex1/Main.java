package assignment1.ex1;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("TEST TẤT CẢ MÁY TURING M1 - M6");
        System.out.println("=========================================\n");

        // Test M1
        System.out.println("--- M1: Cộng 1 ---");
        String[] m1Tests = {"0", "1", "10", "11", "101", "111", "1011"};
        for (String test : m1Tests) {
            M1 m1 = new M1(test);
            String result = m1.run();
            System.out.printf("%s + 1 = %s\n", test, result);
        }

        // Test M2
        System.out.println("\n--- M2: Trừ 1 ---");
        String[] m2Tests = {"1", "10", "11", "100", "101", "110", "1000"};
        for (String test : m2Tests) {
            M2 m2 = new M2(test);
            String result = m2.run();
            System.out.printf("%s - 1 = %s\n", test, result);
        }

        // Test M3
        System.out.println("\n--- M3: Đảo bit ---");
        String[] m3Tests = {"0", "1", "01", "10", "01001", "111000", "101010"};
        for (String test : m3Tests) {
            M3 m3 = new M3(test);
            String result = m3.run();
            System.out.printf("%s -> %s\n", test, result);
        }

        // Test M4
        System.out.println("\n--- M4: Loại bỏ số 0 ---");
        String[] m4Tests = {"0", "1", "00", "01", "10", "01001", "10101", "001100"};
        for (String test : m4Tests) {
            M4 m4 = new M4(test);
            String result = m4.run();
            System.out.printf("%s -> %s\n", test, result);
        }

        // Test M5
        System.out.println("\n--- M5: Kiểm tra đối xứng ---");
        String[] m5Tests = {"0", "1", "00", "01", "010", "0110", "01010", "01100", "010010"};
        for (String test : m5Tests) {
            M5 m5 = new M5(test);
            String result = m5.run();
            System.out.printf("%s -> %s\n", test, result);
        }

        // Test M6
        System.out.println("\n--- M6: Chèn ký tự trắng vào đầu ---");
        String[] m6Tests = {"0", "1", "01", "10", "010", "101", "01001"};
        for (String test : m6Tests) {
            M6 m6 = new M6(test);
            String result = m6.run();
            System.out.printf("'%s' -> '%s'\n", test, result);
        }

        System.out.println("\n✅ Hoàn thành test tất cả các máy!");
    }
}
