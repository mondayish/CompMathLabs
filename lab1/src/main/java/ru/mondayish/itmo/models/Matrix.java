package ru.mondayish.itmo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Matrix {
    private final double[][] koefs;
    private final double[] freeMembers;
}
