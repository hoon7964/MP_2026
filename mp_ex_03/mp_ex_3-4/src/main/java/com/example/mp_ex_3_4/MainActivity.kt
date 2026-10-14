package com.example.mp_ex_3_4

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onResultClick(view: View?) {
        val name = findViewById<EditText>(R.id.etName).text.toString()
        val password = findViewById<EditText>(R.id.etPassword).text.toString()
        val email = findViewById<EditText>(R.id.etEmail).text.toString()
        val date = findViewById<EditText>(R.id.etDate).text.toString()
        val phone = findViewById<EditText>(R.id.etPhone).text.toString()

        val resultView = findViewById<TextView>(R.id.tvResult)

        resultView.text = "성명 - $name\n비밀번호 - $password\n이메일 - $email\n생년월일 - $date\n연락처 - $phone"
    }
}