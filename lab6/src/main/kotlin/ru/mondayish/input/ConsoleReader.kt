package ru.mondayish.input

import ru.mondayish.math.CommonUtils.Companion.MAX_ACCURACY
import ru.mondayish.math.CommonUtils.Companion.MIN_ACCURACY
import ru.mondayish.math.CommonUtils.Companion.functions
import ru.mondayish.models.Input
import java.util.*

class ConsoleReader {

    fun readInput(): Input {
        val funcNumber: Int = getAvailableVariant(IntArray(functions.size) {it+1},
            "Выберите ОДУ:\n1 - ${functions[0].view}\n2 - ${functions[1].view}\n3 - ${functions[2].view}") - 1
        val method: Int = getAvailableVariant(IntArray(2) {it+1},
            "Выберите метод:\n1 - Усовершенствованный метод Эйлера\n2 - Метод Адамса")
        val startConditions: Array<Double> = getDoubleArray("Введите x0 и y0:")
        val xn: Double = getDoubleValue("Введите конец интервала(x_n):")
        val h: Double = getDoubleValue("Введите интервал(h):")
        val accuracy: Double = getDoubleValue("Введите точность([$MIN_ACCURACY - $MAX_ACCURACY]):", MAX_ACCURACY, MIN_ACCURACY)

        return Input(funcNumber, method, startConditions[0], startConditions[1], xn, h, accuracy)
    }

    private fun getDoubleValue(message: String, max: Double = Double.MAX_VALUE, min: Double = Double.MIN_VALUE): Double {
        val scanner = Scanner(System.`in`)
        scanner.useLocale(Locale.US)
        var result: Double = Double.NaN
        while (result.isNaN() || result > max || result < min) {
            println(message)
            result = scanner.nextDouble()
        }
        return result
    }

    private fun getAvailableVariant(variants: IntArray, message: String): Int {
        val scanner = Scanner(System.`in`)
        var variant: Int = -1
        while (!variants.contains(variant)) {
            println(message)
            variant = scanner.nextInt()
        }
        return variant
    }

    private fun getDoubleArray(message: String): Array<Double> {
        println(message)
        return readLine()!!.split(' ').map { it.toDouble() }.toTypedArray()
    }
}