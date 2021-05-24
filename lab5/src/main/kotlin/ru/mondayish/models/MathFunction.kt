package ru.mondayish.models

data class MathFunction(
    val func: (Double) -> Double,
    val view: String,
    val derivative: (Int, Double) -> Double
)
