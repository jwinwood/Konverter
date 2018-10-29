@file:JvmName("ConversionLookUpTable")

package com.jwinwood.kitchenweightconverter

/*
    |  From  |   To   | Conversion Rate |
    --------------------------------------
    | Grams  | Ounces |        0.035274 |
    --------------------------------------
    | Ounces | Grams  |         28.3495 |
    --------------------------------------
 */
val conversionLookUpTable = mapOf(
    Units.GRAMS to mapOf(Units.OUNCES to 0.035274),
    Units.OUNCES to mapOf(Units.GRAMS to 28.3495)
)