package ru.mondayish.itmo.models

data class MathFunction(
    val view: String,
    val func: (Double) -> Double,
    val integral: (Double) -> Double
)
