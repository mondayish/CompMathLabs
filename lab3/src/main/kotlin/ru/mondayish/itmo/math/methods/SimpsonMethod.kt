package ru.mondayish.itmo.math.methods

import ru.mondayish.itmo.models.UserInput

class SimpsonMethod : IntegralCalculationMethod {

    override fun calculate(input: UserInput, splitNumber: Int): Double {
        val h: Double = (input.interval.finish - input.interval.start) / splitNumber
        val simpsonSum: Double = calculateSimpsonSum(splitNumber, h, input)
        return h / 3 * simpsonSum
    }

    private fun calculateSimpsonSum(n: Int, h: Double, input: UserInput): Double {
        var sum: Double = 0.0
        var x: Double = input.interval.start
        for (i in 0..n) {
            sum += if (i == 0 || i == n) input.selectedFunction.func(x)
                   else if (i % 2 == 1) 4 * input.selectedFunction.func(x)
                   else 2 * input.selectedFunction.func(x)
            x += h
        }
        return sum
    }
}
