package ru.mondayish

import ru.mondayish.input.ConsoleReader
import ru.mondayish.input.FileReader
import ru.mondayish.math.FaultCalculator
import ru.mondayish.math.LagrangeMethod
import ru.mondayish.math.NewtonMethod
import ru.mondayish.models.Input
import ru.mondayish.models.MathFunction
import kotlin.math.*

const val MAX_POINTS_NUMBER: Int = 20
const val MIN_POINTS_NUMBER: Int = 5
const val POSITIONS_TO_ROUND: Double = 5.0
val functions: Array<MathFunction> = arrayOf(
    MathFunction(func = {x: Double -> sin(x) }, view = "sin(x)", derivative =
    {n: Int, x: Double -> (-1.0).pow(n + 1) * (if (n % 2 == 0) sin(x) else cos(x))}),
    MathFunction(func = {x: Double -> E.pow(x) }, view = "e^x", derivative =
    {n: Int, x: Double -> E.pow(x) }),
    MathFunction(func = {x: Double -> x*x*x}, view = "x^3", derivative =
    {n: Int, x: Double -> when(n){
        1 -> 3*x*x
        2 -> 6*x
        3 -> 6.0
        else -> 0.0
    }})
)

val round = {x: Double -> (x * 10.0.pow(POSITIONS_TO_ROUND)).roundToInt() / 10.0.pow(POSITIONS_TO_ROUND) }

fun main(args: Array<String>) {
    val input: Input = if (args.isNotEmpty()) FileReader(args[0]).readPoints() else ConsoleReader().readPoints()
    val result: Double = if (input.method == 1) LagrangeMethod().calculate(input.points, input.xToSolve)
        else NewtonMethod().calculate(input.points, input.xToSolve)

    println("============================")
    println("Результат: ${round(result)}")
    if(input.inputType == 2) println("Погрешность Rn(x) <= ${round(FaultCalculator().calculate(input))}")
}










