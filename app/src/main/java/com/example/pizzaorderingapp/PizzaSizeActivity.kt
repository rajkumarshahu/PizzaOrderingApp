package com.example.pizzaorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_pizza_size.*

class PizzaSizeActivity : AppCompatActivity() {
    var order:Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_size)

        // Extract intent data passed from previous activity
        order = intent.extras?.get("pizzaOrder") as Order

        // Populate pizza size
        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            })
    }

    // Method to handle next button on click
    fun handleOnChooseToppingButtonClick(v: View) {
        if (v.id == R.id.btnChooseToppings) {
            // Call pizza toppings activity with order object
            val intent = Intent(this@PizzaSizeActivity, PizzaToppingActivity::class.java)
            val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
            order?.pizzaSize = radio.text.toString()
            intent.putExtra("pizzaOrder", order)
            startActivity(intent)
        }
    }
}