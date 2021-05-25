package ru.mondayish.models

data class Input(
    val inputType: Int,
    val pointsCount: Int,
    val points: Array<Point>,
    val method: Int,
    val xToSolve: Double,
    val funcNumber: Int = -1
)
