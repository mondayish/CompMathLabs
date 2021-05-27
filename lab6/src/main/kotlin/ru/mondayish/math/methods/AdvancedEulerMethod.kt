package ru.mondayish.math.methods

import ru.mondayish.math.CommonUtils.Companion.calculateExactValues
import ru.mondayish.models.Input
import ru.mondayish.models.MethodResult

class AdvancedEulerMethod : DiffMethod {

    override fun calculate(input: Input): MethodResult {
        val xValues = DoubleArray(input.n) {input.x0 + it*input.h}
        val exactYValues: DoubleArray = calculateExactValues(input.func, xValues, input.func.const(input.x0, input.y0))
        val yValues = DoubleArray(input.n) {input.y0}
        val derivativeValues = DoubleArray(input.n)
        val indexes = IntArray(input.n) {it}

        for(i in 1 until input.n) {
            derivativeValues[i-1] = input.func.derivative(xValues[i-1], yValues[i-1])
            yValues[i] = yValues[i-1] + input.h/2 * (derivativeValues[i-1]
                    + input.func.derivative(xValues[i], yValues[i-1]+input.h*derivativeValues[i-1]))
        }
        derivativeValues[input.n-1] = input.func.derivative(xValues[input.n-1], yValues[input.n-1])

        return MethodResult(indexes, xValues, yValues, exactYValues, derivativeValues)
    }

    override fun getAccuracyOrder(): Int {
        return 2
    }
}