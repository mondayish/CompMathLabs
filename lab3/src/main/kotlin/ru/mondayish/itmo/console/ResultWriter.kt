package ru.mondayish.itmo.console

import ru.mondayish.itmo.models.CalculationResult
import java.math.RoundingMode

const val PLACES_TO_ROUND = 6

class ResultWriter {

    fun writeResultToConsole(result: CalculationResult) {
        println("Значение интеграла: ${round(result.result)}")
        println("Число разбиений: ${result.splitNumber}")
        println("Погрешность: ${round(result.fault)}")
    }

    private fun round(number: Double): Double {
        return number.toBigDecimal().setScale(PLACES_TO_ROUND, RoundingMode.HALF_EVEN).toDouble()
    }
}
