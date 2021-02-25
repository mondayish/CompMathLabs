package ru.mondayish.itmo.utils;

import java.util.Scanner;

public class AccuracyUtils {

    private static final double MAX_ACCURACY = 1;
    private static final double MIN_ACCURACY = 0.000001;

    public static double getAccuracy() {
        double accuracy;
        do {
            ConsoleWriter.write("Введите точность (от " + String.format("%.6f", MIN_ACCURACY) + " до " + MAX_ACCURACY + "): ");
            Scanner scanner = new Scanner(System.in);
            accuracy = scanner.nextDouble();
        } while (accuracy < MIN_ACCURACY || accuracy > MAX_ACCURACY);
        return accuracy;
    }
}
