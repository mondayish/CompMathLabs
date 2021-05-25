package ru.mondayish.models

import kotlinx.serialization.Serializable

@Serializable
data class InputJsonModel(
    val inputType: Int,
    val pointsCount: Int,
    val xValues: Array<Double>,
    val yValues: Array<Double> = arrayOf(),
    val funcNumber: Int = -1,
    val method: Int,
    val xToSolve: Double,
)
