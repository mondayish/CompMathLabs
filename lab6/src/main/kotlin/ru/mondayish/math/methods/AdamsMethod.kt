package ru.mondayish.math.methods

import ru.mondayish.math.CommonUtils.Companion.calculateExactValues
import ru.mondayish.models.Input
import ru.mondayish.models.MethodResult
import kotlin.math.pow

private const val FIRST_ELEMENTS_COUNT = 4

class AdamsMethod : DiffMethod {

    override fun calculate(input: Input): MethodResult {
        val xValues = DoubleArray(input.n) {input.x0 + it*input.h}
        val exactYValues: DoubleArray = calculateExactValues(input.func, xValues, input.func.const(input.x0, input.y0))
        val firstYValues = getFirstYValues(input)
        val yValues = DoubleArray(input.n) {if(it < FIRST_ELEMENTS_COUNT) firstYValues[it] else 0.0}
        val derivativeValues = DoubleArray(input.n) {if(it < FIRST_ELEMENTS_COUNT) input.func.derivative(xValues[it], yValues[it]) else 0.0}
        val indexes = IntArray(input.n) {it}

        for(i in FIRST_ELEMENTS_COUNT until input.n) {
            derivativeValues[i-1] = input.func.derivative(xValues[i-1], yValues[i-1])
            val delta1F: Double = derivativeValues[i-1] - derivativeValues[i-2]
            val delta2F: Double = derivativeValues[i-1] - 2*derivativeValues[i-2] + derivativeValues[i-3]
            val delta3F: Double = derivativeValues[i-1] - 3*derivativeValues[i-2] + 3*derivativeValues[i-3] - derivativeValues[i-4]
            yValues[i] = yValues[i-1] + input.h*derivativeValues[i-1] + input.h.pow(2)/2*delta1F +
                    5/12 * input.h.pow(3)*delta2F + 3/8 * input.h.pow(4)*delta3F
        }
        derivativeValues[input.n-1] = input.func.derivative(xValues[input.n-1], yValues[input.n-1])

        return MethodResult(indexes, xValues, yValues, exactYValues, derivativeValues)
    }

    private fun getFirstYValues(input: Input): DoubleArray {
        return RungeKuttaMethod().calculate(Input(input.func, input.method, input.x0, input.y0,
            input.x0+input.h*3, input.h, FIRST_ELEMENTS_COUNT, input.accuracy)).yValues
    }

    override fun getAccuracyOrder(): Int {
        return 4
    }
}