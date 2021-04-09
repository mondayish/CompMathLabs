package ru.mondayish.itmo.math

import ru.mondayish.itmo.SPLIT_NUMBER
import ru.mondayish.itmo.math.methods.IntegralCalculationMethod
import ru.mondayish.itmo.math.methods.RectangleMethod
import ru.mondayish.itmo.math.methods.SimpsonMethod
import ru.mondayish.itmo.math.methods.TrapeziumMethod
import ru.mondayish.itmo.models.CalculationResult
import ru.mondayish.itmo.models.Method
import ru.mondayish.itmo.models.UserInput
import kotlin.math.abs

class IntegralService {

    fun calculateIntegralWithAccuracy(input: UserInput): CalculationResult {
        val calculationMethod: IntegralCalculationMethod = when (input.method) {
            Method.RECTANGLE_MIDDLE, Method.RECTANGLE_LEFT, Method.RECTANGLE_RIGHT -> RectangleMethod()
            Method.TRAPEZIUM -> TrapeziumMethod()
            Method.SIMPSON -> SimpsonMethod()
        }

        var splitNumber: Int = SPLIT_NUMBER
        var i0: Double
        var i1: Double = calculationMethod.calculate(input, splitNumber)
        do {
            i0 = i1
            i1 = calculationMethod.calculate(input, splitNumber * 2)
            splitNumber *= 2
        } while (abs(i0 - i1) > input.accuracy)

        return CalculationResult(i1, splitNumber, abs(i0 - i1))
    }
}
