package ru.mondayish.itmo.methods

import ru.mondayish.itmo.models.UserInput

interface IntegralCalculationMethod {

    fun calculate(input: UserInput, splitNumber: Int): Double
}
