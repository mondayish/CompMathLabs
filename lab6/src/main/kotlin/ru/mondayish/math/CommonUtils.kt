package ru.mondayish.math

import kotlin.math.*

class CommonUtils {
    companion object {
        const val MAX_ACCURACY: Double = 0.1
        const val MIN_ACCURACY: Double = 0.000001

        val functions: Array<MathFunction> = arrayOf(
            MathFunction(view = "y' = y + (x + 1)*y^2", derivative = {x, y -> y + (x+1)*y*y},
                exact = {x, c -> E.pow(x) / (c - x * E.pow(x))}),
            MathFunction(view = "y' = e^(2x) + y", derivative = {x, y -> y + E.pow(2*x)},
                exact = {x, c -> (c + E.pow(x)) * E.pow(x)}),
            MathFunction(view = "y' = sin(x) + y", derivative = {x, y -> sin(x) + y},
                exact = {x, c -> (c - E.pow(-x)*sin(x)/2 - E.pow(-x)*cos(x)/2) * E.pow(x)}))
    }
}