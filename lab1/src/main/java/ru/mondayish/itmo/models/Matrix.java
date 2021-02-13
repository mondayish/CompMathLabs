package ru.mondayish.itmo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Модель матрицы
 */
@AllArgsConstructor
@Getter
public class Matrix {
    private final double[][] koefs;
    private final double[] freeMembers;
}
