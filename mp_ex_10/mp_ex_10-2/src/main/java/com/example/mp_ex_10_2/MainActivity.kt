package com.example.mp_ex_10_2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var calendarView: CalendarView
    lateinit var editText: EditText
    lateinit var btnSave: Button
    lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        calendarView = findViewById(R.id.calendarView)
        editText = findViewById(R.id.edtContent)
        btnSave = findViewById(R.id.save)
        btnDelete = findViewById(R.id.delete)

        var year = Calendar.getInstance().get(Calendar.YEAR)
        var month = Calendar.getInstance().get(Calendar.MONTH) + 1
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var fileName = "${year}_${month}_${day}.txt"

        val diaryContent = readDiary(fileName)
        editText.setText(diaryContent)
        btnSave.isEnabled = true
        calendarView.setOnDateChangeListener { _, newYear, newMonth, newDay ->
            year = newYear
            month = newMonth + 1
            day = newDay
            fileName = "${year}_${month}_${day}.txt"
            val content = readDiary(fileName)
            editText.setText(content)
            btnSave.isEnabled = true
        }
        btnSave.setOnClickListener {
            val outputStream: FileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            val content: String = editText.text.toString()
            outputStream.write(content.toByteArray())
            outputStream.close()
            Toast.makeText(
                applicationContext,
                "$year/$month/$day 일정이 저장되었습니다",
                Toast.LENGTH_SHORT
            ).show()
        }
        btnDelete.setOnClickListener {
            val filePath = applicationContext.filesDir
            val fileList = File(filePath.toString()).listFiles()
            for (file in fileList) {
                if (file.isDirectory) {
                    file.delete()
                } else {
                    file.delete()
                }
            }
            Toast.makeText(this, "모든 일정 파일이 삭제되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
    fun readDiary(filename: String): String {
        var content = ""
        try {
            val inputStream: FileInputStream = openFileInput(filename)
            val text = inputStream.reader().readText()
            inputStream.close()
            content = text.trim()
            btnSave.text = "수정하기"
        } catch (e: IOException) {
            editText.hint = "일정 없음"
            btnSave.text = "저장하기"
        }
        return content
    }
}