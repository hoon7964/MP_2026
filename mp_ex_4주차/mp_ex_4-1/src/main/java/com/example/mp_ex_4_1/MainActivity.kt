package com.example.mp_ex_4_1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diceImage1 = findViewById<ImageView>(R.id.diceImage1)
        val diceImage2 = findViewById<ImageView>(R.id.diceImage2)
        val etSum = findViewById<EditText>(R.id.etSum)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // 주사위 이미지 리소스 배열 선언[cite: 118]
        val diceNumber = intArrayOf(
            R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6
        )

        // 주사위 값 랜덤으로 설정[cite: 118]
        val numA = (Math.random() * 6).roundToInt() + 1
        val numB = (Math.random() * 6).roundToInt() + 1

        diceImage1.setImageResource(diceNumber[numA - 1])
        diceImage2.setImageResource(diceNumber[numB - 1])

        btnSubmit.setOnClickListener {
            val answer = etSum.text.toString().toIntOrNull()
            if (answer == (numA + numB)) {
                Toast.makeText(this, "맞았습니다", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "틀렸습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}