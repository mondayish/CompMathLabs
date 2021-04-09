package ru.mondayish.itmo.math.methods

import ru.mondayish.itmo.models.UserInput

class TrapeziumMethod : IntegralCalculationMethod {

    override fun calculate(input: UserInput, splitNumber: Int): Double {
        val h: Double = (input.interval.finish - input.interval.start) / splitNumber
        val trapezoidSum: Double = calculateTrapezoidSum(splitNumber, h, input)
        return h / 2 * trapezoidSum
    }

    private fun calculateTrapezoidSum(n: Int, h: Double, input: UserInput): Double {
        var sum: Double = 0.0;
        var x: Double = input.interval.start
        for (i in 0..n) {
            val fValue: Double = calculateFunctionValue(h, input, x)
            sum += if(i == 0 || i == n) fValue
                   else 2 * fValue
            x += h
        }
        return sum
    }
}
