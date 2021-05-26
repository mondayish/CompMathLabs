package ru.mondayish.math

data class MathFunction(
    val view: String,
    val derivative: (Double, Double) -> Double,
    val exact: (Double, Double) -> Double
)
