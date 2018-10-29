package com.jwinwood.kitchenweightconverter

object ConverterFactory {

    fun forUnits(from: Units, to: Units): Converter {
        val conversionRate = conversionLookUpTable[from]?.get(to)
        return Converter(conversionRate ?: 1.0)
    }
}