package ru.mondayish.models

data class Input(
    val funcNumber: Int,
    val method: Int,
    val x0: Double,
    val y0: Double,
    val xn: Double,
    val h: Double,
    val accuracy: Double
)
