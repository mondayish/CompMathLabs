package ru.mondayish.console

import ru.mondayish.models.Input

interface PointReader {
    fun readPoints(): Input
}