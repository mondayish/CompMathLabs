package ru.mondayish.itmo.utils;

import ru.mondayish.itmo.models.CalculationResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * Обертка для вывода в консоль, написанная не знаю зачем
 */
public class ConsoleWriter {

    private static final int PLACES_FOR_ROUND = 7;

    public static void writeResultsToConsole(CalculationResult calculationResult) {
        writeln("Вектор неизвестных: " +
                Arrays.toString(Arrays.stream(calculationResult.getXVector()).map(ConsoleWriter::round).toArray()));
        writeln("Вектор погрешностей: " +
                Arrays.toString(Arrays.stream(calculationResult.getFaultVector()).map(ConsoleWriter::round).toArray()));
        writeln("Количество итераций: " + calculationResult.getIterationCount());
    }

    public static void write(String message) {
        System.out.print(message);
    }

    public static void writeln(String message) {
        System.out.println(message);
    }

    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(PLACES_FOR_ROUND, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
