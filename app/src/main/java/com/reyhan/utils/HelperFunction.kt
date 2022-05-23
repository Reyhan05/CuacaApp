package com.reyhan.utils

import java.math.RoundingMode

object HelperFunction {

    fun formatterDegree(temp: Double?): String {
        val temprature = temp as Double
        val tempToCelcius = temprature - 273.0
        val formatDegree = tempToCelcius.toBigDecimal().setScale(2, RoundingMode.CEILING)
        return "$formatDegree °C"
    }
}