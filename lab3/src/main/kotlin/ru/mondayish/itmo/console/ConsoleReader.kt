package ru.mondayish.itmo.console

import ru.mondayish.itmo.functions
import ru.mondayish.itmo.models.Interval
import ru.mondayish.itmo.models.MathFunction
import ru.mondayish.itmo.models.UserInput
import java.io.InputStream
import java.util.*

class ConsoleReader {

    fun readUserInput(inputStream: InputStream = System.`in`): UserInput {
        val scanner: Scanner = Scanner(inputStream)
        println("Доступные функции:")
        functions.forEachIndexed { index: Int, mathFunction: MathFunction -> println("${index + 1}) ${mathFunction.view}") }
        print("Введите номер нужной функции: ")
        var selectedFuncIndex: Int
        do {
            selectedFuncIndex = scanner.nextInt()
        } while (selectedFuncIndex < 1 || selectedFuncIndex > functions.size)
        val selectedFunction: MathFunction = functions.get(selectedFuncIndex - 1)
        print("Введите пределы интегрирования: ")
        val start: Double = scanner.nextDouble()
        val finish: Double = scanner.nextDouble()
        print("Введите точность: ")
        val accuracy: Double = scanner.nextDouble()
        return UserInput(selectedFunction, Interval(start, finish), accuracy)
    }
}
