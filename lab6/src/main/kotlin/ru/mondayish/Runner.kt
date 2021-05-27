package ru.mondayish

import ru.mondayish.console.ConsoleReader
import ru.mondayish.console.ResultWriter
import ru.mondayish.math.AdamsMethod
import ru.mondayish.math.AdvancedEulerMethod
import ru.mondayish.math.DiffMethod
import ru.mondayish.models.Input
import ru.mondayish.models.MethodResult

fun main(args: Array<String>) {
    val input: Input = ConsoleReader().readInput()
    val diffMethod: DiffMethod = if(input.method == 1) AdvancedEulerMethod() else AdamsMethod()
    val methodResult: MethodResult = diffMethod.calculate(input)
    ResultWriter().writeResult(input, methodResult)
}

