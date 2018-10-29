package com.jwinwood.kitchenweightconverter

import org.junit.Test

class TestSuite {

    @Test
    fun test() {
        val converter = ConverterFactory.forUnits(from = Units.GRAMS, to = Units.OUNCES)

        assert(converter.convert(1.0) == 0.035274)
    }

    @Test
    fun test2() {
        val converter = ConverterFactory.forUnits(from = Units.OUNCES, to = Units.GRAMS)

        assert(converter.convert(1.0) == 28.3495)
    }
}
