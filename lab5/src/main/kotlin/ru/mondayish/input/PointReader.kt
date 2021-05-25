package ru.mondayish.input

import ru.mondayish.models.Input

interface PointReader {

    fun readPoints(): Input
}