package ru.mondayish.math

import ru.mondayish.models.Input
import kotlin.math.pow

class RungeCalculator {

    fun calculateR(input: Input): Double {
        val method: DiffMethod = if(input.method == 1) AdvancedEulerMethod() else AdamsMethod()
        val yh = method.calculate(input).yValues[2]
        val y2h = method.calculate(Input(input.func, input.method, input.x0, input.y0,
            input.xn, input.h * 2, input.n, input.accuracy)).yValues[1]

        return (yh - y2h) / (2.0.pow(method.getAccuracyOrder()) - 1)
    }
}