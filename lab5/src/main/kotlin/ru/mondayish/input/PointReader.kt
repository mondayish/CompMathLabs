package ru.mondayish.input

import ru.mondayish.models.Point

interface PointReader {

    fun readPoints(): Array<Point>
}