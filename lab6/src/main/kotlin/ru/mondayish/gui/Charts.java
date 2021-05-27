package ru.mondayish.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import ru.mondayish.models.Input;
import ru.mondayish.models.MethodResult;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Charts {

    public static void draw(Input input, MethodResult methodResult){
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(ChartSeries.getRealSeries(input, methodResult));
        dataset.addSeries(ChartSeries.getCalculatedSeries(methodResult));
        JFreeChart chart = ChartFactory.createXYLineChart("Численное дифференцирование", "X",
                "Y", dataset, PlotOrientation.VERTICAL, true, true, false);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) chart.getXYPlot().getRenderer();
        renderer.setDefaultStroke(new BasicStroke(5.0f));
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesShape(1, new Ellipse2D.Double(-2.0, -2.0, 4.0, 4.0));

        JFrame jFrame = new JFrame("Lab6");
        jFrame.getContentPane().add(new ChartPanel(chart));
        jFrame.setSize(1000, 600);
        jFrame.setVisible(true);
    }
}
