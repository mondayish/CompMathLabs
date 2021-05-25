package ru.mondayish.math

import ru.mondayish.math.CommonUtils.Companion.functions
import ru.mondayish.models.Input
import kotlin.math.abs

class FaultCalculator {

    fun calculate(input: Input): Double {
        val maxFValue: Double = input.points
            .map { functions[input.funcNumber - 1].derivative(input.pointsCount + 1, it.x) }.toDoubleArray().maxOrNull() ?: 0.0
        return maxFValue / (1..input.pointsCount+1).reduce {a, b -> a*b} *
                abs(input.points.map { input.xToSolve - it.x }.reduce {a, b -> a*b})
    }
}