package com.example.mp_ex_3_2

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        textView.text = "Hello World!"
        textView.setTextColor(Color.parseColor("#03A9F4"))
        textView.typeface = Typeface.SERIF
        textView.textSize = 50f
    }
}