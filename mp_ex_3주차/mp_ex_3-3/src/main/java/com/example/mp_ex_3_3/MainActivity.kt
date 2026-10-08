package com.example.mp_ex_3_3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onButtonClick(view: View?) {
        val num1 = 200
        val num2 = 300
        val sum = num1 + num2

        Toast.makeText(applicationContext, "합계: $sum", Toast.LENGTH_SHORT).show()
    }
}