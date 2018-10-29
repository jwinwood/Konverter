package com.jwinwood.kitchenweightconverter

import android.os.Bundle
import android.support.annotation.ArrayRes
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val fromUnitsSpinner: Spinner by lazy { findViewById<Spinner>(R.id.spinner_from_units) }
    private val toUnitsSpinner: Spinner by lazy { findViewById<Spinner>(R.id.spinner_to_units) }

    private val fromAmountEdit: EditText by lazy { findViewById<EditText>(R.id.edit_from_amount) }

    private val convertedAmountText: TextView by lazy { findViewById<TextView>(R.id.text_converted_amount) }

    private var fromUnits: Units = Units.GRAMS
    private var toUnits: Units = Units.GRAMS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fromAmountEdit.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                convert()
                return@OnKeyListener true
            }
            false
        })

        setUpSpinner(fromUnitsSpinner,
                     R.array.units_of_measure_array,
                     object : AdapterView.OnItemSelectedListener {
                         override fun onNothingSelected(parent: AdapterView<*>?) {
                             fromUnits = Units.GRAMS
                             convert()
                         }

                         override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                             fromUnits = Units.values()[position]
                             convert()
                         }
                     })

        setUpSpinner(toUnitsSpinner,
                     R.array.units_of_measure_array,
                     object : AdapterView.OnItemSelectedListener {
                         override fun onNothingSelected(parent: AdapterView<*>?) {
                             toUnits = Units.GRAMS
                             convert()
                         }

                         override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                             toUnits = Units.values()[position]
                             convert()
                         }
                     })
    }

    private fun setUpSpinner(spinner: Spinner, @ArrayRes resId: Int, listener: AdapterView.OnItemSelectedListener?) {
        ArrayAdapter.createFromResource(
            this,
            resId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = listener
        }
    }

    private fun convert() {
        val converter = ConverterFactory.forUnits(fromUnits, toUnits)
        val fromAmount = fromAmountEdit.text.toString().toDouble()

        val convertedAmount = converter.convert(fromAmount)

        val decimalFormat = DecimalFormat("###.##")
        decimalFormat.roundingMode = RoundingMode.HALF_DOWN

        convertedAmountText.text = decimalFormat.format(convertedAmount)
    }
}
