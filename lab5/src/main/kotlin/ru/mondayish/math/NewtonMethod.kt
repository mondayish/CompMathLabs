package ru.mondayish.math

import ru.mondayish.models.Point

class NewtonMethod : InterpolationMethod {

    override fun calculate(points: Array<Point>, x: Double): Double {
        fun f(i1: Int, i2: Int): Double {
            if (i1 == i2) return 0.0
            return if (i2 - i1 == 1) (points[i2].y - points[i1].y) / (points[i2].x - points[i1].x)
            else (f(i1 + 1, i2) - f(i1, i2 - 1)) / (points[i2].x - points[i1].x)
        }

        return points[0].y + points.mapIndexed { i: Int, _: Point ->
            f(0, i) * points.mapIndexed { index, point -> if (index < i) x - point.x else 1.0 }.reduce { a, b -> a * b }
        }.toDoubleArray().sum()
    }
}