package com.example.mp_ex_10_4

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insert)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.insert)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var cateResult: String = ""
        val InsertAddBtn : Button = findViewById(R.id.button_insert)
        val InsertListBtn : Button = findViewById(R.id.button_list)
        val radio_group : RadioGroup = findViewById(R.id.radio_group)
        val purposeEditTxt : EditText = findViewById(R.id.edit_purpose)
        var DateString = ""
        val DateSetBtn : Button= findViewById(R.id.button_calendar)
        val TodayBtn : Button= findViewById(R.id.button_today)
        val moneyEditTxt :EditText= findViewById(R.id.edit_money)
        val contentEidtTxt :EditText= findViewById(R.id.edit_content)
        val dateTxt : TextView = findViewById(R.id.textview_date)
        printDate(dateTxt)
        cateResult ="지출"
        TodayBtn.setOnClickListener {
            printDate(dateTxt)
        }
        DateSetBtn.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                var monthStr:String=(i2+1).toString()
                var dayStr:String=i3.toString()
                if((i2 + 1)<10){
                    monthStr= "0"+(i2 + 1).toString()
                }
                if(i3<10){
                    dayStr = "0"+i3
                }
                dateTxt.text = i.toString() + "-" + monthStr + "-" + dayStr
            }
            val datePickerDialog =
                DatePickerDialog(this, listener, year, month, day)
            datePickerDialog.show()
        }
        radio_group.setOnCheckedChangeListener { group, chekcedId ->
            when (chekcedId) {
                R.id.radio_income -> cateResult = "수입"
                R.id.radio_expense -> cateResult = "지출"
            }
        }
        InsertAddBtn.setOnClickListener {
            DateString = dateTxt.text.toString()

            val houseKDT = HouseKeeping(
                0,
                cateResult,
                purposeEditTxt.text.toString().trim(),
                DateString,
                moneyEditTxt.text.toString().toInt(),
                contentEidtTxt.text.toString().trim()
            )

            val dbHelper = DBHelper(this)
            dbHelper.insert(houseKDT)
            Toast.makeText(this.applicationContext, "저장되었습니다", Toast.LENGTH_SHORT)
            purposeEditTxt.text.clear()
            moneyEditTxt.text.clear()
            contentEidtTxt.text.clear()
        }
        InsertListBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun printDate(dateTxt:TextView){
        val calendar: Calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)+1
        val year = calendar.get(Calendar.YEAR)
        var monthStr:String=month.toString()
        var dayStr:String=day.toString()
        if( month <10){
            monthStr= "0"+month

        }
        if(day < 10){
            dayStr = "0"+day
        }
        dateTxt.text = year.toString() +"-" + monthStr +"-"+dayStr
    }
}