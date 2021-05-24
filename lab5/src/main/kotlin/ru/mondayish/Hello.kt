package ru.mondayish

import ru.mondayish.models.MathFunction
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin

val functions: Array<MathFunction> = arrayOf(
    MathFunction(func = {x: Double -> sin(x) }, view = "sin(x)", derivative =
    {n: Int, x: Double -> (-1.0).pow(n + 1) * (if (n % 2 == 0) sin(x) else cos(x))}),
    MathFunction(func = {x: Double -> exp(x) }, view = "e^x", derivative =
    {n: Int, x: Double -> exp(x) }),
    MathFunction(func = {x: Double -> x*x*x}, view = "x^3", derivative =
    {n: Int, x: Double -> when(n){
        1 -> 3*x*x
        2 -> 6*x
        3 -> 6.0
        else -> 0.0
    }})
)

fun main(args: Array<String>) {
    println("Hello, World")
}