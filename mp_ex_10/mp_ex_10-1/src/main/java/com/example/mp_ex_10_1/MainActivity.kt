package com.example.mp_ex_10_1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var ivNumA: ImageView
    lateinit var ivNumB: ImageView
    lateinit var ivBackground: ImageView
    lateinit var etAnswer: EditText
    lateinit var btnReset: Button
    lateinit var btnResult: Button
    lateinit var tvMessage: TextView
    var numberImages = intArrayOf(
        R.drawable.num0, R.drawable.num1, R.drawable.num2,
        R.drawable.num3, R.drawable.num4, R.drawable.num5,
        R.drawable.num6, R.drawable.num7, R.drawable.num8,
        R.drawable.num9
    )
    var correctCount: Int = 0
    var totalCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnReset = findViewById(R.id.button_reset)
        btnResult = findViewById(R.id.button_result)
        etAnswer = findViewById(R.id.editText_answer)
        tvMessage = findViewById(R.id.textView_message)
        ivBackground = findViewById(R.id.imageView_bg)
        loadGameStats()
        var result = generateQuestion()
        btnReset.setOnClickListener {
            val pref: SharedPreferences = getSharedPreferences("gamefile", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.clear()
            editor.apply()
            loadGameStats()
        }

        btnResult.setOnClickListener {
            totalCount += 1
            val answer = etAnswer.text.toString()
            //정답 판별
            if (answer.isNotEmpty() && answer.toInt() == result) {
                Toast.makeText(applicationContext, "맞았습니다", Toast.LENGTH_SHORT).show()
                correctCount += 1
                ivBackground.setImageResource(R.drawable.bg_ok)
            } else {
                Toast.makeText(applicationContext, "틀렸습니다", Toast.LENGTH_SHORT).show()
                ivBackground.setImageResource(R.drawable.bg_no)
            }
            //SharedPreferences에 결과 저장
            val pref: SharedPreferences = getSharedPreferences("gamefile", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = pref.edit()
            editor.putInt("total", totalCount)
            editor.putInt("grade", correctCount)
            editor.apply()
            //새로운 문제 출제 및 UI 업데이트
            loadGameStats()
            result = generateQuestion()
            etAnswer.text.clear()
        }
    }


    fun generateQuestion(): Int {
        val partA = (Math.random() * 9).roundToInt()
        val partB = (Math.random() * 9).roundToInt()
        val result = partA * partB
        ivNumA = findViewById(R.id.imageView_num1)
        ivNumB = findViewById(R.id.imageView_num2)
        ivNumA.setImageResource(numberImages[partA])
        ivNumB.setImageResource(numberImages[partB])
        return result
    }

    fun loadGameStats() {
        val pref: SharedPreferences = getSharedPreferences("gamefile", Context.MODE_PRIVATE)
        totalCount = pref.getInt("total", 0)
        correctCount = pref.getInt("grade", 0)
        if (totalCount > 0) {
            val percent = (correctCount.toDouble() / totalCount.toDouble()) * 100
            tvMessage.text = "당신의 정답율은 %.2f (%d/%d) %% 입니다".format(percent, correctCount,
                totalCount)
        } else {
            tvMessage.text = "당신의 정답율은 0% 입니다"
        }
    }

}