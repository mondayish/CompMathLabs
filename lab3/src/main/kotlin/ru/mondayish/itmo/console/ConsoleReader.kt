package ru.mondayish.itmo.console

import ru.mondayish.itmo.functions
import ru.mondayish.itmo.models.Interval
import ru.mondayish.itmo.models.MathFunction
import ru.mondayish.itmo.models.Method
import ru.mondayish.itmo.models.UserInput
import java.io.InputStream
import java.util.*

class ConsoleReader {

    fun readUserInput(inputStream: InputStream = System.`in`): UserInput {
        val scanner: Scanner = Scanner(inputStream)
        println("Доступные методы:")
        Method.values().forEachIndexed {index: Int, method: Method ->  println("${index+1}) ${method.textPresentation}")}
        print("Введите номер нужного метода: ")
        val selectedMethod: Method = Method.values()[getIntInBounds(1, Method.values().size, scanner)-1]
        println("Доступные функции:")
        functions.forEachIndexed { index: Int, mathFunction: MathFunction -> println("${index + 1}) ${mathFunction.view}") }
        print("Введите номер нужной функции: ")
        val selectedFunction: MathFunction = functions[getIntInBounds(1, functions.size, scanner) - 1]
        print("Введите пределы интегрирования: ")
        val start: Double = scanner.nextDouble()
        val finish: Double = scanner.nextDouble()
        print("Введите точность: ")
        val accuracy: Double = scanner.nextDouble()
        return UserInput(selectedMethod, selectedFunction, Interval(start, finish), accuracy)
    }

    fun getIntInBounds(start: Int, finish:Int, scanner: Scanner): Int{
        var selectedIndex: Int
        do {
            selectedIndex = scanner.nextInt()
        } while (selectedIndex < start || selectedIndex > finish)
        return selectedIndex;
    }
}
