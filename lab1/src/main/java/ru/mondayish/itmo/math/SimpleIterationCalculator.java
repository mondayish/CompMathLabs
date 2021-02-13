package ru.mondayish.itmo.math;

import lombok.AllArgsConstructor;
import ru.mondayish.itmo.models.Matrix;

@AllArgsConstructor
public class SimpleIterationCalculator {

    private final Matrix matrix;
    private final double neededAccuracy;
}
