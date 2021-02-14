package ru.mondayish.itmo.utils;

import ru.mondayish.itmo.models.CalculationResult;

import java.util.Arrays;

/**
 * Обертка для вывода в консоль, написанная не знаю зачем
 */
public class ConsoleWriter {

    public static void writeResultsToConsole(CalculationResult calculationResult) {
        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.writeln("Вектор неизвестных: " + Arrays.toString(calculationResult.getXVector()));
        consoleWriter.writeln("Вектор погрешностей: " + Arrays.toString(calculationResult.getFaultVector()));
        consoleWriter.writeln("Количество итераций: " + calculationResult.getIterationCount());
    }

    public void write(String message) {
        System.out.print(message);
    }

    public void writeln(String message) {
        System.out.println(message);
    }
}
