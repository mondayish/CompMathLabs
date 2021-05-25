package ru.mondayish.math

import ru.mondayish.models.Point

class LagrangeMethod {

    fun calculate(points: Array<Point>, x: Double): Double {
        val lCfs: DoubleArray = points.mapIndexed {i: Int, p: Point ->
            val filteredPoints: Array<Point> = points.filterIndexed { index, _ -> index != i }.toTypedArray()
            p.y * filteredPoints.map { x - it.x }.reduce {a, b -> a * b } /
                    filteredPoints.map { p.x - it.x }.reduce {a, b -> a * b}
        }.toDoubleArray()
        return lCfs.sum()
    }
}