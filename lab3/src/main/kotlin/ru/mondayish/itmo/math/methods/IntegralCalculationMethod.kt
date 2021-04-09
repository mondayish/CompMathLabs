package ru.mondayish.itmo.math.methods

import ru.mondayish.itmo.models.UserInput

interface IntegralCalculationMethod {

    fun calculate(input: UserInput, splitNumber: Int): Double

    fun calculateFunctionValue(h: Double, input: UserInput, x: Double) : Double {
        val foundedPoint = input.selectedFunction.cutPoints.findLast { point -> point.value == x }
        return if(foundedPoint != null){
            (input.selectedFunction.func(x+input.accuracy) + input.selectedFunction.func(x-input.accuracy))/2
        } else input.selectedFunction.func(x)
    }
}
