package com.example.l2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var loanAmount: EditText
    private lateinit var interestRate: EditText
    private lateinit var result: TextView
    private lateinit var loanPeriodText: TextView
    private var loanPeriod: Int = 0

    private val loanPeriodValues = intArrayOf(3, 6, 9, 12, 24, 36, 48)
    private lateinit var loanPeriodSeekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loanAmount = findViewById(R.id.loanAmount)
        interestRate = findViewById(R.id.interestRate)
        result = findViewById(R.id.result)
        loanPeriodText = findViewById(R.id.loanPeriodText)

        loanPeriodSeekBar = findViewById(R.id.loanPeriodSeekBar)
        loanPeriodSeekBar.max = loanPeriodValues.size - 1

        loanPeriodSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                loanPeriod = loanPeriodValues[progress]
                loanPeriodText.text = "$loanPeriod мес."
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Действия при начале перемещения ползунка
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Действия при окончании перемещения ползунка
            }
        })

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener { calculateEMI() }
    }

    private fun calculateEMI() {
        val P = loanAmount.text.toString().toDouble()
        val r = (interestRate.text.toString().toDouble() / 12 / 100)
        val n = loanPeriod.toDouble()
        val emi = (P * r * pow(1 + r, n)) / (pow(1 + r, n) - 1)
        result.text = String.format("Ежемесячный платеж: %.2f", emi)
    }
}
