package ru.mondayish.itmo

import ru.mondayish.itmo.console.ConsoleReader
import ru.mondayish.itmo.console.ResultWriter
import ru.mondayish.itmo.math.IntegralService
import ru.mondayish.itmo.models.CalculationResult
import ru.mondayish.itmo.models.MathFunction
import ru.mondayish.itmo.models.UserInput
import kotlin.math.*


const val SPLIT_NUMBER: Int = 4
val AVAILABLE_FUNCTIONS: Array<MathFunction> = arrayOf(
    MathFunction("-2x^3 - 4x^2 + 8x - 4", { x: Double -> -2 * x.pow(3) - 4 * x * x + 8 * x - 4 },
        { x:Double -> -x.pow(4)/2 - 4*x.pow(3)/3 + 4*x*x - 4*x }) ,
    MathFunction("x^2 + 4x - 17", { x: Double -> x * x + 4 * x - 17 },
        { x: Double -> x.pow(3)/3 + 2*x*x - 17*x }),
    MathFunction("cos^2(2x)*sin^3(3x)", { x: Double -> cos(2 * x).pow(2) * sin(3*x).pow(3) },
        {x: Double -> 3*cos(x)/16 - cos(3 * x)/8 + cos(5 * x) / 80 - 3* cos(7*x)/112 + cos(9*x)/72 + cos(13 * x)/208 }),
    MathFunction("sqrt(x^2/7 + 3)", { x: Double -> sqrt(x.pow(2) / 7 + 3) },
        {x: Double -> (sqrt(x*x + 21) * x + 21/ sinh(x/ sqrt(21.0)))/(2* sqrt(7.0)) })
)

fun main(args: Array<String>) {
    val userInput: UserInput = ConsoleReader().readUserInput(System.`in`)
    val calculationResult: CalculationResult = IntegralService().calculateIntegralWithAccuracy(userInput)
    ResultWriter().writeResultToConsole(calculationResult)
}

