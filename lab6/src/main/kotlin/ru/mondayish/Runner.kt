package ru.mondayish

import ru.mondayish.console.ConsoleReader
import ru.mondayish.console.ResultWriter
import ru.mondayish.gui.Charts
import ru.mondayish.math.CommonUtils.Companion.validateValues
import ru.mondayish.math.methods.AdamsMethod
import ru.mondayish.math.methods.AdvancedEulerMethod
import ru.mondayish.math.methods.DiffMethod
import ru.mondayish.models.Input
import ru.mondayish.models.MethodResult

fun main(args: Array<String>) {
    val input: Input = ConsoleReader().readInput()
    val diffMethod: DiffMethod = if(input.method == 1) AdvancedEulerMethod() else AdamsMethod()
    val methodResult: MethodResult = diffMethod.calculate(input)
    validateValues(methodResult)
    Charts.draw(input, methodResult)
    ResultWriter().writeResult(input, methodResult)
}

