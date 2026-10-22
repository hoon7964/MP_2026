package com.example.mp_ex_6_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameActivity : AppCompatActivity() {
    var imageviewId = intArrayOf(
        R.id.image01, R.id.image02, R.id.image03,
        R.id.image04, R.id.image05, R.id.image06,
        R.id.image07, R.id.image08, R.id.image09
    )
    var framelayoutId = intArrayOf(
        R.id.frame_layout01, R.id.frame_layout02, R.id.frame_layout03,
        R.id.frame_layout04, R.id.frame_layout05, R.id.frame_layout06,
        R.id.frame_layout07, R.id.frame_layout08, R.id.frame_layout09
    )
    var ivObj : Array<ImageView?> = arrayOfNulls<ImageView>(9)
    var flayoutObj : Array<FrameLayout?> = arrayOfNulls<FrameLayout>(9)

    var imgArray : Array<ImageView?> = arrayOfNulls<ImageView>(9)
    var selectNum = IntArray(9)
    var mShape = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mShape = intent.getIntExtra("shape", 0)

        val shapeObj : ImageView = findViewById(R.id.imageView)

        if (mShape == 1)
            shapeObj.setImageResource(R.drawable.circle)
        else if (mShape == 2)
            shapeObj.setImageResource(R.drawable.square)

        for (i in 0..8) {
            selectNum[i] = 0
            ivObj[i] = findViewById(imageviewId[i])
            flayoutObj[i] = findViewById(framelayoutId[i])
        }
        var num = 0

        for (i in 0..8) {
            ivObj[i]?.setOnClickListener {
                if (selectNum[i] == 1) {
                    selectNum[i] = 0
                    --num
                    flayoutObj[i]?.removeView(imgArray[i])
                }
                else {
                    if (num < 3) {
                        selectNum[i] = 1
                        num++
                        imgArray[i] = ImageView(this)
                        imgArray[i]?.setImageResource(R.drawable.ok_check)
                        flayoutObj[i]?.addView(imgArray[i])
                    } else Toast.makeText(
                        this@GameActivity,
                        "3개 까지만 선택 가능합니다",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val btnObj: Button = findViewById(R.id.button)
        btnObj.setOnClickListener {

            val outIntent = Intent()
            outIntent.putExtra("resId", selectNum)

            setResult(RESULT_OK, outIntent)
            finish()
        }
    }
}