package ru.mondayish.itmo

import ru.mondayish.itmo.console.ConsoleReader
import ru.mondayish.itmo.console.ResultWriter
import ru.mondayish.itmo.math.IntegralService
import ru.mondayish.itmo.models.*
import kotlin.math.*


const val SPLIT_NUMBER: Int = 4
val AVAILABLE_FUNCTIONS: Array<MathFunction> = arrayOf(
    MathFunction("3/x + 2", { x: Double -> 3/x + 2 }, { x: Double -> 2*x + 3 * ln(x) },
        listOf(CutPoint(0.0, CutPointType.SECOND))),
    MathFunction("-2x^3 - 4x^2 + 8x - 4", { x: Double -> -2 * x.pow(3) - 4 * x * x + 8 * x - 4 },
        { x:Double -> -x.pow(4)/2 - 4*x.pow(3)/3 + 4*x*x - 4*x }, emptyList()),
    MathFunction("arctg(9/x+3)", { x: Double -> atan(9/x + 3) },
        { x: Double -> x* atan(9/x + 3) - 9/20 * (6 * atan(10*x/9 + 3) - ln(10*x*x + 54*x + 81) )},
        listOf(CutPoint(0.0, CutPointType.FIRST))),
    MathFunction("cos^2(2x)*sin^3(3x)", { x: Double -> cos(2 * x).pow(2) * sin(3*x).pow(3) },
        {x: Double -> 3*cos(x)/16 - cos(3 * x)/8 + cos(5 * x) / 80 - 3* cos(7*x)/112 + cos(9*x)/72 + cos(13 * x)/208 },
        emptyList()),
    MathFunction("sqrt(x^2/7 + 3)", { x: Double -> sqrt(x.pow(2) / 7 + 3) },
        {x: Double -> (sqrt(x*x + 21) * x + 21/ sinh(x/ sqrt(21.0)))/(2* sqrt(7.0)) }, emptyList())
)

fun main(args: Array<String>) {
    val userInput: UserInput = ConsoleReader().readUserInput(System.`in`)
    val calculationResult: CalculationResult = IntegralService().calculateIntegralWithAccuracy(userInput)
    ResultWriter().writeResultToConsole(calculationResult)
}

