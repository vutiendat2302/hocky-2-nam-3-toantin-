package assignment5_backtracking_brute_force;

public class Sudoku {

    private static final int SIZE = 9;
    private boolean[][] rowUsed = new boolean[SIZE][SIZE + 1];
    private boolean[][] colUsed = new boolean[SIZE][SIZE + 1];
    private boolean[][] boxUsed = new boolean[SIZE][SIZE + 1];
    private int[][] board;

    /**
     * Cho ma tran co 9x9 gom cac so tu 0 -> 9
     * So 0 tuong trung cho khoang trong
     * In ra ma tran so sau khi da thay the cac so 0 bang cac so tu 1 -> 9
     * Ma tran phai thoa man cac quy luat cua tro choi sudoku
     * Moi hang phai co du cac so tu 1 -> 9
     * Moi cot phai co du cac so tu 1 _> 9
     * chia ma tran thanh 9 ma tran con co 3x3 thi moi ma tran con do
     * cung phai chua du cac so tu 1 -> 9
     *
     * y tuong:  su dung backtracking de thu tung gia tri hop ly cho moi o trong. Neu mot lua chon dan den sai, quay lui
     * va thu gia tri khac
     *
     * @param grid ma tran de sudoku
     *
     *
     */


    public void solveSudoku(int[][] grid) {
        this.board = grid;
        initializeUsedArrays();

        if (solve()) {
            printBoard();
        } else {
            System.out.println("Khong co loi giai!");
        }
    }

    private void initializeUsedArrays() {
        // Reset mảng (mặc dù Java khởi tạo false)
        for (int r = 0; r < SIZE; r++) {
            for (int num = 1; num <= SIZE; num++) {
                rowUsed[r][num] = false;
                colUsed[r][num] = false;
                boxUsed[r][num] = false;
            }
        }

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int num = board[r][c];
                if (num != 0) {
                    rowUsed[r][num] = true;
                    colUsed[c][num] = true;
                    int box = getBoxIndex(r, c);
                    boxUsed[box][num] = true;
                }
            }
        }
    }

    /**
     * Tính chỉ số khối 3x3 (0..8) từ tọa độ (r, c).
     */
    private int getBoxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    /**
     * Hàm đệ quy quay lui tìm lời giải.
     * @return true nếu tìm được lời giải, false nếu không.
     */
    private boolean solve() {
        // Tìm ô trống có ít lựa chọn nhất (MRV)
        int[] bestCell = findBestEmptyCell();
        if (bestCell == null) {
            // Không còn ô trống -> đã giải xong
            return true;
        }

        int r = bestCell[0];
        int c = bestCell[1];
        int box = getBoxIndex(r, c);

        // Thử các số từ 1 đến 9
        for (int num = 1; num <= SIZE; num++) {
            if (!rowUsed[r][num] && !colUsed[c][num] && !boxUsed[box][num]) {
                // Đặt số
                board[r][c] = num;
                rowUsed[r][num] = true;
                colUsed[c][num] = true;
                boxUsed[box][num] = true;

                // Đệ quy
                if (solve()) {
                    return true;
                }

                // Quay lui
                board[r][c] = 0;
                rowUsed[r][num] = false;
                colUsed[c][num] = false;
                boxUsed[box][num] = false;
            }
        }

        return false; // Không có số nào hợp lệ
    }


    /**
     * Tìm ô trống có ít lựa chọn hợp lệ nhất (Minimum Remaining Values heuristic).
     * @return mảng [row, col] của ô được chọn, hoặc null nếu không còn ô trống.
     */
    private int[] findBestEmptyCell() {
        int minOptions = Integer.MAX_VALUE;
        int[] bestCell = null;

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == 0) {
                    int box = getBoxIndex(r, c);
                    int options = 0;
                    // Đếm số lựa chọn hợp lệ
                    for (int num = 1; num <= SIZE; num++) {
                        if (!rowUsed[r][num] && !colUsed[c][num] && !boxUsed[box][num]) {
                            options++;
                        }
                    }
                    // Nếu không có lựa chọn nào -> bế tắc ngay, trả về ô này để fail sớm
                    if (options == 0) {
                        return new int[]{r, c};
                    }
                    // Cập nhật ô có ít lựa chọn nhất
                    if (options < minOptions) {
                        minOptions = options;
                        bestCell = new int[]{r, c};
                    }
                }
            }
        }
        return bestCell; // có thể null nếu bảng đầy
    }

    /**
     * In bảng Sudoku ra màn hình.
     */
    private void printBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                System.out.print(board[r][c]);
                if (c < SIZE - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
