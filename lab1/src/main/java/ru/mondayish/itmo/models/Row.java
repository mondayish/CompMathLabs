package ru.mondayish.itmo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Row {

    private final double[] koefs;
    private final double freeMember;
}
