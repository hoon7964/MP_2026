package com.example.mp_ex_10_3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var btnPrev: Button
    private lateinit var btnNext: Button
    private lateinit var myPicture: ImageView
    private lateinit var tvNumber: TextView
    private var curNum = 0
    private lateinit var imageFiles: Array<File>
    private var imageFname: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnPrev = findViewById(R.id.btnPrev)
        btnNext = findViewById(R.id.btnNext)
        tvNumber = findViewById(R.id.tvNumber)
        myPicture = findViewById(R.id.imvPicture)

        val picturesDir = getExternalFilesDir("MyFileStorage")
        imageFiles = picturesDir?.listFiles { file -> file.name.endsWith(".jpg") ||
                file.name.endsWith(".png") } ?: emptyArray()
        if (imageFiles.isEmpty()) {
            Toast.makeText(this, "이미지가 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        showImage(curNum)
        //이전 버튼 클릭 이벤트
        btnPrev.setOnClickListener {
            if (curNum <= 0) curNum = imageFiles.size - 1
            else curNum -= 1
            showImage(curNum)
        }
        //다음 버튼 클릭 이벤트
        btnNext.setOnClickListener {
            if (curNum >= imageFiles.size - 1) curNum = 0
            else curNum += 1
            showImage(curNum)
        }
    }
    private fun showImage(index: Int) {
        val imageFile = imageFiles[index]
        if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            myPicture.setImageBitmap(bitmap)
            tvNumber.text = "${index + 1}/${imageFiles.size}"
        } else {
            Toast.makeText(this, "이미지를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}