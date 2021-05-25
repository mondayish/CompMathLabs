package ru.mondayish.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import ru.mondayish.models.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Charts {

    public static void draw(Input input){
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(ChartSeries.getPointsSeries(input));
        if(input.getMethod() == 1) dataset.addSeries(ChartSeries.getLagrangeSeries(input));
        else dataset.addSeries(ChartSeries.getNewtonSeries(input));
        if(input.getInputType() == 2) dataset.addSeries(ChartSeries.getFunctionSeries(input));

        JFreeChart chart = ChartFactory.createXYLineChart("Интерполяция функции", "X", "Y",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) chart.getXYPlot().getRenderer();
        renderer.setDefaultStroke(new BasicStroke(5.0f));
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
        renderer.setSeriesPaint(2, Color.DARK_GRAY);

        JFrame jFrame = new JFrame("Lab5");
        jFrame.getContentPane().add(new ChartPanel(chart));
        jFrame.setSize(1000, 600);
        jFrame.setVisible(true);
    }

}
