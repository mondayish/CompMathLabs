package ru.mondayish.itmo

import org.junit.Assert
import org.junit.Test
import ru.mondayish.itmo.math.IntegralService
import ru.mondayish.itmo.models.*

const val TEST_ACCURACY: Double = 0.01
const val EXPECTED_RESULT: Double = 8.8333
val TEST_FUNCTION = MathFunction("x^2 + 3x +2") { x: Double -> x * x + 3 * x + 2 }
val TEST_INTERVAL: Interval = Interval(1.0, 2.0)

class IntegralServiceTest {

    @Test
    fun rectangleRightMethodTest(){
        val result: CalculationResult = IntegralService().calculateIntegralWithAccuracy(
            UserInput(Method.RECTANGLE_RIGHT, TEST_FUNCTION, TEST_INTERVAL, TEST_ACCURACY))
        Assert.assertEquals(EXPECTED_RESULT, result.result, TEST_ACCURACY)
    }

    @Test
    fun rectangleLeftMethodTest(){
        val result: CalculationResult = IntegralService().calculateIntegralWithAccuracy(
            UserInput(Method.RECTANGLE_LEFT, TEST_FUNCTION, TEST_INTERVAL, TEST_ACCURACY))
        Assert.assertEquals(EXPECTED_RESULT, result.result, TEST_ACCURACY)
    }

    @Test
    fun rectangleMiddleMethodTest(){
        val result: CalculationResult = IntegralService().calculateIntegralWithAccuracy(
            UserInput(Method.RECTANGLE_MIDDLE, TEST_FUNCTION, TEST_INTERVAL, TEST_ACCURACY))
        Assert.assertEquals(EXPECTED_RESULT, result.result, TEST_ACCURACY)
    }

    @Test
    fun trapeziumMethodTest(){
        val result: CalculationResult = IntegralService().calculateIntegralWithAccuracy(
            UserInput(Method.TRAPEZIUM, TEST_FUNCTION, TEST_INTERVAL, TEST_ACCURACY))
        Assert.assertEquals(EXPECTED_RESULT, result.result, TEST_ACCURACY)
    }

    @Test
    fun simpsonMethodTest(){
        val result: CalculationResult = IntegralService().calculateIntegralWithAccuracy(
            UserInput(Method.SIMPSON, TEST_FUNCTION, TEST_INTERVAL, TEST_ACCURACY))
        Assert.assertEquals(EXPECTED_RESULT, result.result, TEST_ACCURACY)
    }
}
