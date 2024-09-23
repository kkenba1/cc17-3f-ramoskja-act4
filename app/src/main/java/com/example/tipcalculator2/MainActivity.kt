package com.example.tipcalculator2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcalculator2.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculateTip() {
        val cost = binding.serviceCost.text.toString().toDouble()
        val selectedId = binding.optionTip.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.choice_twenty_percent -> 0.2
            R.id.choice_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = cost*tipPercentage
        val roundUp = binding.roundUpTip.isChecked
        if (roundUp) {
            tip = ceil(tip)
        }
        val currencyTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = currencyTip
    }
}