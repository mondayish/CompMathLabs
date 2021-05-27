package ru.mondayish.math.methods

import ru.mondayish.math.CommonUtils.Companion.calculateExactValues
import ru.mondayish.models.Input
import ru.mondayish.models.MethodResult

class RungeKuttaMethod : DiffMethod {

    override fun calculate(input: Input): MethodResult {
        val xValues = DoubleArray(input.n) {input.x0 + it*input.h}
        val exactYValues: DoubleArray = calculateExactValues(input.func, xValues, input.func.const(input.x0, input.y0))
        val yValues = DoubleArray(input.n) {input.y0}
        val indexes = IntArray(input.n) {it}

        for(i in 1 until input.n) {
            val k1 = input.h * input.func.derivative(xValues[i-1], yValues[i-1])
            val k2 = input.h * input.func.derivative(xValues[i-1]+input.h/2, yValues[i-1]+k1/2)
            val k3 = input.h * input.func.derivative(xValues[i-1]+input.h/2, yValues[i-1]+k2/2)
            val k4 = input.h * input.func.derivative(xValues[i-1]+input.h, yValues[i-1]+k3)
            yValues[i] = yValues[i-1] + (k1 + 2*k2 + 2*k3 + k4)/6
        }

        return MethodResult(indexes, yValues, xValues, exactYValues)
    }

    override fun getAccuracyOrder(): Int {
        return 4
    }
}