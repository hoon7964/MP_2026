package com.example.mp_ex_8_2

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit  var ivQestion : ImageView
    lateinit  var ivAnswer : ImageView
    lateinit  var ivAmPm : ImageView
    lateinit  var resultTime : String
    lateinit  var resultAMPM : String
    var question =0
    var ampm =0
    var drawableQuestion = intArrayOf(
        R.drawable.question1,   R.drawable.question2,   R.drawable.question3,
        R.drawable.question4,   R.drawable.question5
    )
    var drawableAnswerAM = intArrayOf(
        R.drawable.answer11,  R.drawable.answer12,   R.drawable.answer13,
        R.drawable.answer14,   R.drawable.answer15
    )
    var drawableAnswerPM = intArrayOf(
        R.drawable.answer21,    R.drawable.answer22,  R.drawable.answer23,
        R.drawable.answer24,    R.drawable.answer25
    )
    var timeAnswerAM =  arrayOf(
        "9:15", "2:25", "10:35", "11:40", "3:10"
    )
    var timeAnswerPM =  arrayOf(
        "21:15", "14:25", "22:35", "23:40", "15:10"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ivQestion = findViewById(R.id.question)
        ivAnswer = findViewById(R.id.answer)
        ivAmPm = findViewById(R.id.ampm)
        question = (Math.random() * 3).roundToInt()
        ampm = (Math.random() * 1).roundToInt()
        ivQestion.setImageResource(drawableQuestion[question])

        if (ampm==0) {
            ivAmPm.setImageResource(R.drawable.time_am)
        }
        else if (ampm==1) {
            ivAmPm.setImageResource(R.drawable.time_pm)
        }
        val buttonStart: Button = findViewById(R.id.buttonStart)

        buttonStart.setOnClickListener {
            question = (Math.random() * 3).roundToInt()
            ampm = (Math.random() * 1).roundToInt()
            ivQestion.setImageResource(drawableQuestion[question])
            if (ampm==0) {
                ivAmPm.setImageResource(R.drawable.time_am)
            }
            else if (ampm==1) {
                ivAmPm.setImageResource(R.drawable.time_pm)
            }
            ivAnswer.isVisible = false
        }
        val buttonTime: Button = findViewById(R.id.buttonTime)

        buttonTime.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val listener = TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                var state = "AM"
                var selHour = i
                var selMinute = i2
                if (selHour > 12) {
                    state = "PM"
                }
                resultAMPM = state
                resultTime = selHour.toString() + ":" + selMinute.toString()
                if(resultAMPM=="AM")

                    if( resultTime.equals(timeAnswerAM[question])) {
                        ivAnswer.isVisible = true
                        ivAnswer.setImageResource(drawableAnswerAM[question])
                        Toast.makeText(applicationContext, "맞았습니다", Toast.LENGTH_LONG).show()
                    }
                    else Toast.makeText(applicationContext, "틀렸습니다", Toast.LENGTH_LONG).show()
                else if(resultAMPM=="PM")

                    if( resultTime.equals(timeAnswerPM[question])) {
                        ivAnswer.isVisible = true
                        ivAnswer.setImageResource(drawableAnswerPM[question])
                        Toast.makeText(applicationContext, "맞았습니다", Toast.LENGTH_LONG).show()
                    }
                    else Toast.makeText(applicationContext, "틀렸습니다" ,
                        Toast.LENGTH_LONG).show()
            }

            val timePickerDialog = TimePickerDialog(this, R.style.TimePickerStyle, listener, hour,
                minute, true)
            timePickerDialog.show()
        }
    }
}