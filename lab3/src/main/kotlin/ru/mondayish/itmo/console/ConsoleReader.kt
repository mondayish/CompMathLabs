package ru.mondayish.itmo.console

import ru.mondayish.itmo.AVAILABLE_FUNCTIONS
import ru.mondayish.itmo.models.Interval
import ru.mondayish.itmo.models.MathFunction
import ru.mondayish.itmo.models.Method
import ru.mondayish.itmo.models.UserInput
import java.io.InputStream
import java.util.*

const val MAX_ACCURACY = 0.1
const val MIN_ACCURACY = 0.0000001

class ConsoleReader {

    fun readUserInput(inputStream: InputStream = System.`in`): UserInput {
        val scanner: Scanner = Scanner(inputStream)

        println("Доступные методы:")
        Method.values().forEachIndexed {index: Int, method: Method ->  println("${index+1}) ${method.textPresentation}")}
        val selectedMethod: Method = Method.values()[getNumberInBounds(1.0, Method.values().size.toDouble(), scanner, "Введите номер нужного метода: ").toInt()-1]

        println("Доступные функции:")
        AVAILABLE_FUNCTIONS.forEachIndexed { index: Int, mathFunction: MathFunction -> println("${index + 1}) ${mathFunction.view}") }
        val selectedFunction: MathFunction = AVAILABLE_FUNCTIONS[getNumberInBounds(1.0, AVAILABLE_FUNCTIONS.size.toDouble(), scanner, "Введите номер нужной функции: ").toInt() - 1]

        print("Введите пределы интегрирования: ")
        val start: Double = scanner.nextDouble()
        val finish: Double = scanner.nextDouble()

        val accuracy: Double = getNumberInBounds(MIN_ACCURACY, MAX_ACCURACY, scanner, "Введите точность: ")

        return UserInput(selectedMethod, selectedFunction, Interval(start, finish), accuracy)
    }

    private fun getNumberInBounds(start: Double, finish: Double, scanner: Scanner, message: String): Double {
        var result: Double
        do {
            print(message)
            result = scanner.nextDouble()
        } while (result < start || result > finish)
        return result
    }
}
