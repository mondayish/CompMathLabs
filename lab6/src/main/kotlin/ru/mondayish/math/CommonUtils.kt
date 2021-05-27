package ru.mondayish.math

import ru.mondayish.exceptions.InputException
import ru.mondayish.models.MathFunction
import ru.mondayish.models.MethodResult
import kotlin.math.*

class CommonUtils {
    companion object {
        const val MAX_ACCURACY: Double = 0.1
        const val MIN_ACCURACY: Double = 0.000001
        const val MIN_INTERVAL_COUNT: Int = 4
        const val MAX_INTERVAL_COUNT: Int = 100000

        val functions: Array<MathFunction> = arrayOf(
            MathFunction(view = "y' = y + (x + 1)*y^2", derivative = { x, y -> y + (x + 1) * y * y },
                exact = { x, c -> E.pow(x) / (c - x * E.pow(x)) }, const = { x, y -> E.pow(x) / y + x * E.pow(x) }),
            MathFunction(view = "y' = e^(2x) + y", derivative = { x, y -> y + E.pow(2 * x) },
                exact = { x, c -> (c + E.pow(x)) * E.pow(x) }, const = { x, y -> y / E.pow(x) - E.pow(x) }),
            MathFunction(view = "y' = sin(x) + y", derivative = { x, y -> sin(x) + y },
                exact = { x, c -> (c - E.pow(-x) * sin(x) / 2 - E.pow(-x) * cos(x) / 2) * E.pow(x) },
                const = { x, y -> y / E.pow(x) + E.pow(-x) * sin(x) / 2 + E.pow(-x) * cos(x) / 2 })
        )

        fun roundToFixed(x: Double, pos: Int): String {
            return (round(x * 10.0.pow(pos)) / 10.0.pow(pos)).toString()
        }

        fun calculateExactValues(func: MathFunction, xValues: DoubleArray, c: Double): DoubleArray {
            return xValues.map { func.exact(it, c) }.toDoubleArray()
        }

        fun foundDecimalPLaces(x: Double): Int {
            var result = 1
            while (10.0.pow(-result) > x) result+=1
            return result
        }

        fun validateValues(methodResult: MethodResult){
            methodResult.exactValues.forEach { if(it.isInfinite() || it.isNaN())
                throw InputException("Ошибка вычисления, выберите другой интервал!!!") }
            methodResult.derivativeValues.forEach { if(it.isInfinite() || it.isNaN())
                throw InputException("Ошибка вычисления, выберите другой интервал!!!") }
        }
    }
}