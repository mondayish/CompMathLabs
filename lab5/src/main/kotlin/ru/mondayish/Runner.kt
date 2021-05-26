package ru.mondayish

import ru.mondayish.gui.Charts
import ru.mondayish.input.ConsoleReader
import ru.mondayish.input.FileReader
import ru.mondayish.math.FaultCalculator
import ru.mondayish.math.LagrangeMethod
import ru.mondayish.math.NewtonMethod
import ru.mondayish.models.Input
import ru.mondayish.math.CommonUtils.Companion.round

fun main(args: Array<String>) {
    val input: Input = if (args.isNotEmpty()) FileReader(args[0]).readPoints() else ConsoleReader().readPoints()
    val result: Double = if (input.method == 1) LagrangeMethod().calculate(input.points, input.xToSolve)
        else NewtonMethod().calculate(input.points, input.xToSolve)
    Charts.draw(input)

    println("============================")
    println("Результат: ${round(result)}")
    if(input.inputType == 2) println("Погрешность R_n(x) <= ${round(FaultCalculator().calculate(input))}")
}










