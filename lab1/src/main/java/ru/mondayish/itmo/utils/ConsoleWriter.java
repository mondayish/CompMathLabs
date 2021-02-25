package ru.mondayish.itmo.utils;

import ru.mondayish.itmo.models.CalculationResult;

import java.util.Arrays;

/**
 * Обертка для вывода в консоль, написанная не знаю зачем
 */
public class ConsoleWriter {

    public static void writeResultsToConsole(CalculationResult calculationResult) {
        writeln("Вектор неизвестных: " + Arrays.toString(calculationResult.getXVector()));
        writeln("Вектор погрешностей: " + Arrays.toString(calculationResult.getFaultVector()));
        writeln("Количество итераций: " + calculationResult.getIterationCount());
    }

    public static void write(String message) {
        System.out.print(message);
    }

    public static void writeln(String message) {
        System.out.println(message);
    }
}
