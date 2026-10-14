package com.example.mp_ex_4_4

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var etobjA: EditText
    lateinit var etObjB: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etobjA = findViewById(R.id.etobjA)
        etObjB = findViewById(R.id.etobjB)

        findViewById<Button>(R.id.btnCheck).setOnClickListener {
            onClickChoice(it)
        }
    }

    fun onClickChoice(view: View?) {
        try {
            val partA = etobjA.text.toString().toInt()
            val partB = etObjB.text.toString().toInt()

            if (partA == 2 && partB == 5) {
                Toast.makeText(this, "맞았습니다", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "틀렸습니다", Toast.LENGTH_SHORT).show()
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}