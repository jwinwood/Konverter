package com.jwinwood.kitchenweightconverter

class Converter(private val conversionRate: Double) {

    fun convert(amount: Double): Double {
        return amount * conversionRate
    }
}