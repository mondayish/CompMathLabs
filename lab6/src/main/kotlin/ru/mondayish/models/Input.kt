package ru.mondayish.models

data class Input(
    val func: MathFunction,
    val method: Int,
    val x0: Double,
    val y0: Double,
    val xn: Double,
    val h: Double,
    val n: Int,
    val accuracy: Double
)
