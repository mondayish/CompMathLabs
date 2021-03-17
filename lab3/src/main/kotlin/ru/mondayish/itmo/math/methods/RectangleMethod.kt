package ru.mondayish.itmo.math.methods

import ru.mondayish.itmo.models.Method
import ru.mondayish.itmo.models.UserInput

class RectangleMethod : IntegralCalculationMethod {

    override fun calculate(input: UserInput, splitNumber: Int): Double {
        val h: Double = (input.interval.finish - input.interval.start) / splitNumber
        val rectangleSum: Double = calculateRectangleSum(splitNumber, h, input)
        return h * rectangleSum
    }

    private fun calculateRectangleSum(n: Int, h: Double, input: UserInput): Double {
        var sum: Double = 0.0
        var x: Double = when (input.method) {
            Method.RECTANGLE_LEFT -> input.interval.start
            Method.RECTANGLE_RIGHT -> input.interval.start + h
            Method.RECTANGLE_MIDDLE -> input.interval.start + h / 2
            else -> throw IllegalArgumentException()
        }
        for (i in 0 until n) {
            sum += input.selectedFunction.func(x)
            x += h
        }
        return sum
    }
}
