package ru.mondayish.input

import ru.mondayish.exceptions.InputException
import ru.mondayish.math.CommonUtils.Companion.MAX_POINTS_NUMBER
import ru.mondayish.math.CommonUtils.Companion.MIN_POINTS_NUMBER
import ru.mondayish.math.CommonUtils.Companion.functions
import ru.mondayish.models.Input
import ru.mondayish.models.Point
import java.util.*

class ConsoleReader : PointReader {

    override fun readPoints(): Input {
        val inputType: Int = getAvailableVariant(IntArray(2) {it+1}, "Выберите формат ввода:\n1 - Таблица значений\n2 - На основе функции")
        val pointsCount: Int = getAvailableVariant(IntArray(MAX_POINTS_NUMBER - MIN_POINTS_NUMBER + 1) {it+MIN_POINTS_NUMBER},
            "Введите кол-во точек([$MIN_POINTS_NUMBER - $MAX_POINTS_NUMBER]):")

        val xValues: Array<Double>
        val yValues: Array<Double>
        var funcNumber: Int = -1
        if(inputType == 2) {
            funcNumber = getAvailableVariant(IntArray(functions.size) {it+1},
                "Выберите функцию:\n1 - y = ${functions[0].view}\n2 - y = ${functions[1].view}\n3 - y = ${functions[2].view}")
            xValues = getDoubleArray("Введите значения X:")
            yValues = xValues.map { functions[funcNumber - 1].func(it) }.toTypedArray()
        } else {
            xValues = getDoubleArray("Введите значения X:")
            yValues = getDoubleArray("Введите значения Y:")
        }

        if(xValues.size != pointsCount || yValues.size != pointsCount) throw InputException(message = "Некорректный ввод!!!")

        val method: Int = getAvailableVariant(
            IntArray(2) {it+1},
            "Выберите метод:\n1 - Многочлен Лагранжа\n2 - Многочлен Ньютона с разделенными разностями")
        val xToSolve: Double = getDoubleValue("Введите X для расчета:")

        val points: Array<Point> = Array(pointsCount) { Point(xValues[it], yValues[it]) }
        return Input(inputType, pointsCount, points, method, xToSolve, funcNumber)
    }

    private fun getDoubleValue(message: String): Double {
        val scanner = Scanner(System.`in`)
        scanner.useLocale(Locale.US)
        println(message)
        return scanner.nextDouble()
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