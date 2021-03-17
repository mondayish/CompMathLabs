package ru.mondayish.itmo.models

data class UserInput(
    val method: Method,
    val selectedFunction: MathFunction,
    val interval: Interval,
    val accuracy: Double)
