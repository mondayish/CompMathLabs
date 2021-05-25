package ru.mondayish.gui;

import org.jfree.data.xy.XYSeries;
import ru.mondayish.math.CommonUtils;
import ru.mondayish.math.InterpolationMethod;
import ru.mondayish.math.LagrangeMethod;
import ru.mondayish.math.NewtonMethod;
import ru.mondayish.models.Input;
import ru.mondayish.models.MathFunction;
import ru.mondayish.models.Point;

import java.util.Arrays;

public class ChartSeries {

    private static final int INTERVAL_COUNT = 100;

    public static XYSeries getPointsSeries(Input input){
        XYSeries pointsSeries = new XYSeries("points");
        Arrays.stream(input.getPoints()).forEach(point -> pointsSeries.add(point.getX(), point.getY()));
        return pointsSeries;
    }

    public static XYSeries getFunctionSeries(Input input){
        MathFunction function = CommonUtils.Companion.getFunctions()[input.getFuncNumber() - 1];
        XYSeries funcSeries = new XYSeries("y = "+function.getView());

        double[] characteristics = calculateStepAndMinMax(input);
        double min = characteristics[0], max = characteristics[1], step = characteristics[2];
        for(double i = min - step * 5; i < max + step * 5; i+=step){
            funcSeries.add(i, function.getFunc().invoke(i));
        }
        return funcSeries;
    }

    public static XYSeries getLagrangeSeries(Input input){
        return getSeriesByInterpolationMethod(input, new LagrangeMethod(), "lagrange");
    }

    public static XYSeries getNewtonSeries(Input input){
        return getSeriesByInterpolationMethod(input, new NewtonMethod(), "newton");
    }

    private static XYSeries getSeriesByInterpolationMethod(Input input, InterpolationMethod method, String seriesName) {
        XYSeries series = new XYSeries(seriesName);
        double[] characteristics = calculateStepAndMinMax(input);
        double min = characteristics[0], max = characteristics[1], step = characteristics[2];
        for(double i = min - step * 5; i < max + step * 5; i+=step){
            series.add(i, method.calculate(input.getPoints(), i));
        }
        return series;
    }

    private static double[] calculateStepAndMinMax(Input input){
        double max = Arrays.stream(input.getPoints()).mapToDouble(Point::getX).max().orElse(-1);
        double min = Arrays.stream(input.getPoints()).mapToDouble(Point::getX).min().orElse(-1);
        return new double[] {min, max, CommonUtils.Companion.getRound().invoke((max - min) / INTERVAL_COUNT)};
    }
}
