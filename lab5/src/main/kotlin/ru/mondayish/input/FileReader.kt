package ru.mondayish.input

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.mondayish.MAX_POINTS_NUMBER
import ru.mondayish.MIN_POINTS_NUMBER
import ru.mondayish.exceptions.InputException
import ru.mondayish.functions
import ru.mondayish.models.Input
import ru.mondayish.models.InputJsonModel
import ru.mondayish.models.Point
import java.io.File
import java.nio.charset.Charset

class FileReader(private val path: String) : PointReader {

    override fun readPoints(): Input {
        val jsonString: String = File(path).readText(Charset.defaultCharset())
        val format = Json { ignoreUnknownKeys = true }
        val data: InputJsonModel = format.decodeFromString(jsonString)

        if(data.pointsCount < MIN_POINTS_NUMBER || data.pointsCount > MAX_POINTS_NUMBER)
            throw InputException(message = "Кол-во точек не входит в допустимы интервал!!!")
        if(data.pointsCount != data.xValues.size || data.inputType == 1 && data.pointsCount != data.yValues.size ||
                data.inputType == 2 && !IntArray(3) {it+1}.contains(data.funcNumber))
            throw InputException(message = "Ошибка в файле!!!")
        val yValues: Array<Double> = if(data.inputType == 1) data.yValues
                                    else data.xValues.map { functions[data.funcNumber - 1].func(it) }.toTypedArray()

        val points: Array<Point> = Array(data.pointsCount) { Point(data.xValues[it], yValues[it]) }
        return Input(data.inputType, data.pointsCount, points, data.method, data.xToSolve, data.funcNumber)
    }
}