package assignment0.ex1;

public class SortMetrics implements SortResult {
    public long comparisons = 0;
    public long swaps = 0;
    public double times = 0.0;

    @Override
    public long getComparisons() {
        return comparisons;
    }

    @Override
    public long getSwaps() {
        return swaps;
    }

    @Override
    public double getTimes() {
        return times;
    }

    @Override
    public void print() {
        System.out.printf("Thời gian: %8.3f ms | So sánh: %12d | Hoán đổi/Gán: %10d\n", times, comparisons, swaps);
    }
}
