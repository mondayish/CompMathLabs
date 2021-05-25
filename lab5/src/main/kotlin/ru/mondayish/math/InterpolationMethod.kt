package ru.mondayish.math

import ru.mondayish.models.Point

interface InterpolationMethod {
    fun calculate(points: Array<Point>, x: Double): Double
}