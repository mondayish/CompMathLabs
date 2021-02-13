package ru.mondayish.itmo.utils;

/**
 * Обертка для вывода в консоль, написанная не знаю зачем
 */
public class ConsoleWriter {

    public void write(String message) {
        System.out.print(message);
    }

    public void writeln(String message) {
        System.out.println(message);
    }
}
