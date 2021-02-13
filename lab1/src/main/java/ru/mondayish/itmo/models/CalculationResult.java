package ru.mondayish.itmo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CalculationResult {

    private final double[] xVector;
    private final double[] faultVector;
    private final int iterationCount;
}
