package ru.mondayish.math.methods

import ru.mondayish.models.Input
import ru.mondayish.models.MethodResult

interface DiffMethod {
    fun calculate(input: Input): MethodResult

    fun getAccuracyOrder(): Int
}