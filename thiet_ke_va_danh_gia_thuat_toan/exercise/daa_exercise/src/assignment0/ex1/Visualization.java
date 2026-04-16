package assignment0.ex1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*; // Import thêm để dùng Color và BasicStroke
import java.util.Map;

public class Visualization extends JFrame {

    public Visualization(String title, Map<String, long[]> data, int[] nValues) {
        super(title);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String algo : data.keySet()) {
            long[] times = data.get(algo);
            for (int i = 0; i < nValues.length; i++) {
                dataset.addValue(times[i], algo, String.valueOf(nValues[i]));
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "So sánh thời gian chạy các thuật toán sắp xếp",
                "Kích thước N",
                "Thời gian (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // =========================================================
        // PHẦN TÙY CHỈNH GIAO DIỆN (UI CUSTOMIZATION)
        // =========================================================

        // 1. Đổi màu nền bên ngoài thành màu trắng
        chart.setBackgroundPaint(Color.WHITE);

        // Lấy đối tượng Plot (vùng chứa đồ thị bên trong)
        CategoryPlot plot = chart.getCategoryPlot();

        // Đổi màu nền vùng vẽ đồ thị thành trắng
        plot.setBackgroundPaint(Color.WHITE);

        // Thêm đường kẻ lưới (gridlines) màu xám nhạt để dễ dóng hàng
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);

        // 2. Làm dày các đường line
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        for (int i = 0; i < dataset.getRowCount(); i++) {
            // Thay đổi 2.5f thành số lớn hơn nếu muốn nét dày hơn nữa
            renderer.setSeriesStroke(i, new BasicStroke(2.5f));
        }

        // 3. Giới hạn khoảng giá trị của trục Y (Thời gian) từ 0 đến 40 ms
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(0.0, 40);

        // =========================================================

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public static void showChart(Map<String, long[]> data, int[] nValues) {
        Visualization chart = new Visualization("Sorting Performance", data, nValues);
        chart.setSize(800, 600);
        chart.setLocationRelativeTo(null);
        chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chart.setVisible(true);
    }
}