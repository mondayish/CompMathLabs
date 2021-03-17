package ru.mondayish.itmo.models

data class CalculationResult(
    val result: Double,
    val splitNumber: Int,
    val fault: Double
)
