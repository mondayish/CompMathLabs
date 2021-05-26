package ru.mondayish.math

import ru.mondayish.models.Input
import ru.mondayish.models.MethodResult

class AdamsMethod : DiffMethod {

    override fun calculate(input: Input): MethodResult {

    }

    override fun getAccuracyOrder(): Int {
        return 4
    }
}