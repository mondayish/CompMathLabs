package ru.mondayish.itmo.math.methods

import ru.mondayish.itmo.models.UserInput

class DirectIntegralCalculationMethod : IntegralCalculationMethod {

    override fun calculate(input: UserInput, splitNumber: Int): Double {
        return input.selectedFunction.integral(input.interval.finish) -
                input.selectedFunction.integral(input.interval.start)
    }
}