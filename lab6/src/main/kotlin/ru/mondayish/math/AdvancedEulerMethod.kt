package ru.mondayish.math

import ru.mondayish.math.CommonUtils.Companion.calculateExactValues
import ru.mondayish.math.CommonUtils.Companion.functions
import ru.mondayish.models.Input
import ru.mondayish.models.MathFunction
import ru.mondayish.models.MethodResult
import kotlin.math.floor
import kotlin.math.roundToInt

class AdvancedEulerMethod : DiffMethod {

    override fun calculate(input: Input): MethodResult {
        val func: MathFunction = functions[input.funcNumber]
        val c: Double = func.getConst(input.x0, input.y0)
        val n: Int = floor((input.xn - input.x0) / input.h).roundToInt() + 1
        val xValues: DoubleArray = DoubleArray(n) {input.x0 + it*input.h}
        val exactYValues: DoubleArray = calculateExactValues(func, xValues, c)
        val yValues: DoubleArray = arrayOf(input.y0).toDoubleArray()
        val derivativeValues: DoubleArray = DoubleArray(n)
        val indexes: IntArray = IntArray(n) {it}

        for(i in 1 until n) {
            derivativeValues[i-1] = func.derivative(xValues[i-1], yValues[i-1])
            yValues[i] = yValues[i-1] + input.h/2 * (derivativeValues[i-1]
                    + func.derivative(xValues[i], yValues[i-1]+input.h*derivativeValues[i-1]))
        }
        derivativeValues[n-1] = Double.NaN

        return MethodResult(indexes, xValues, yValues, derivativeValues, exactYValues)
    }

    override fun getAccuracyOrder(): Int {
        return 2
    }
}