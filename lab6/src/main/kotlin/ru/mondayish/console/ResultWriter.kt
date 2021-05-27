package ru.mondayish.console

import ru.mondayish.math.CommonUtils
import ru.mondayish.math.CommonUtils.Companion.roundToFixed
import ru.mondayish.math.RungeCalculator
import ru.mondayish.models.Input
import ru.mondayish.models.MethodResult

class ResultWriter {

    fun writeResult(input: Input, methodResult: MethodResult) {
        val dp: Int = CommonUtils.foundDecimalPLaces(input.accuracy)
        println("=========================")
        println("%-15s %-15s %-15s %-15s %-15s".format("i", "x_i", "y_i", "f(x_i, y_i)", "Точное"))

        methodResult.indexes.forEach {
            println("%-15s %-15s %-15s %-15s %-15s".format(it.toString(),
                roundToFixed(methodResult.xValues[it], dp),
                roundToFixed(methodResult.yValues[it], dp),
                roundToFixed(methodResult.derivativeValues[it], dp),
                roundToFixed(methodResult.exactValues[it], dp)
            )) }
        println("Погрешность по правилу Рунге: ${roundToFixed(RungeCalculator().calculateR(input), 10)}")
    }
}