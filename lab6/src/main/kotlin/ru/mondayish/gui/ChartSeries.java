package ru.mondayish.gui;

import org.jfree.data.xy.XYSeries;
import ru.mondayish.math.CommonUtils;
import ru.mondayish.models.Input;
import ru.mondayish.models.MethodResult;

import java.util.Arrays;

public class ChartSeries {

    private static final int INTERVAL_COUNT = 100;

    public static XYSeries getCalculatedSeries(MethodResult methodResult) {
        XYSeries calculatedSeries = new XYSeries("calculated");
        Arrays.stream(methodResult.getIndexes())
                .forEach(i -> calculatedSeries.add(methodResult.getXValues()[i], methodResult.getYValues()[i]));
        return calculatedSeries;
    }

    public static XYSeries getRealSeries(Input input, MethodResult methodResult) {
        XYSeries realSeries = new XYSeries("real");
        double step = Double.parseDouble(CommonUtils.Companion.roundToFixed(input.getXn() - input.getX0(), 5))
                / INTERVAL_COUNT;
        double c = input.getFunc().getConst().invoke(input.getX0(), input.getY0());

        for (double i = input.getX0() - step * 5; i < input.getXn() + step * 5; i += step) {
            realSeries.add(i, input.getFunc().getExact().invoke(i, c));
        }
        return realSeries;
    }
}
