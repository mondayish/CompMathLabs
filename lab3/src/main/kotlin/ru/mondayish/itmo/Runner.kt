package ru.mondayish.itmo

import ru.mondayish.itmo.models.MathFunction
import kotlin.math.cos
import kotlin.math.pow

val functions: Array<MathFunction> = arrayOf(
    MathFunction("-2x^3 - 4x^2 + 8x - 4") { x: Double -> -2 * x.pow(3) - 4 * x * x + 8 * x - 4 },
    MathFunction("x^2 + 4x - 17") { x: Double -> x * x + 4 * x - 17 },
    MathFunction("cos(2x)/(x^2 + 1)") { x: Double -> cos(2 * x) / (x * x + 1) },
    MathFunction("sqrt(x^4/7 + 3)") { x: Double -> x.pow(4) / 7 + 3 }
)

fun main(args: Array<String>) {
    println("Hello, World")
}

