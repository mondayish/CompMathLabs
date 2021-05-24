package ru.mondayish.input

import ru.mondayish.functions
import ru.mondayish.models.Point
import java.util.*

private const val MAX_POINTS_NUMBER: Int = 20
private const val MIN_POINTS_NUMBER: Int = 5

class ConsoleReader : PointReader {

    override fun readPoints(): Array<Point> {
        val inputType: Int = getAvailableVariant(IntArray(2) {it+1}, "Выберите формат ввода:\n1 - Таблица значений\n2 - На основе функции")
        val pointsCount: Int = getAvailableVariant(IntArray(MAX_POINTS_NUMBER - MIN_POINTS_NUMBER + 1) {it+MIN_POINTS_NUMBER},
            "Введите кол-во точек([$MIN_POINTS_NUMBER - $MAX_POINTS_NUMBER]):")

        val xValues: Array<Double>
        val yValues: Array<Double>
        if(inputType == 2) {
            val funcNumber: Int = getAvailableVariant(IntArray(functions.size) {it+1},
                "Выберите функцию:\n1 - y = ${functions[0].view}\n2 - y = ${functions[1].view}\n3 - y = ${functions[2].view}")
            xValues = getDoubleArray("Введите значения X:")
            yValues = xValues.map { functions[funcNumber].func(it) }.toTypedArray()
        } else {
            xValues = getDoubleArray("Введите значения X:")
            yValues = getDoubleArray("Введите значения Y:")
        }

        val method: Int = getAvailableVariant(
            IntArray(2) {it+1},
            "Выберите метод:\n1 - Многочлен Лагранжа\n2 - Многочлен Ньютона с разделенными разностями")

        return Array(pointsCount) { Point(xValues[it], yValues[it]) }
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