package ru.mondayish.models

data class MethodResult(
    val indexes: IntArray,
    val xValues: DoubleArray,
    val yValues: DoubleArray,
    val derivativeValues: DoubleArray,
    val exactValues: DoubleArray
)
