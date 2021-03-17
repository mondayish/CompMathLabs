package ru.mondayish.itmo.models

enum class Method(val textPresentation: String) {
    RECTANGLE_LEFT("Метод левых прямоугольников"),
    RECTANGLE_RIGHT("Метод правых прямоугольников"),
    RECTANGLE_MIDDLE("Метод средних прямоугольников"),
    TRAPEZIUM("Метод трапеций"),
    SIMPSON("Метод Симпсона")
}
