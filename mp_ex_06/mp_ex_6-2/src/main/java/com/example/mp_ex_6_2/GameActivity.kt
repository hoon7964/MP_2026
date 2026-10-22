package com.example.mp_ex_6_2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class GameActivity : AppCompatActivity() {
    var imageviewCatId = intArrayOf(
        R.id.ivCheck11, R.id.ivCheck12, R.id.ivCheck13,
        R.id.ivCheck14, R.id.ivCheck15
    )
    var imageviewDogId = intArrayOf(
        R.id.ivCheck21, R.id.ivCheck22, R.id.ivCheck23,
        R.id.ivCheck24, R.id.ivCheck25
    )
    var checkObj : Array<ImageView?> = arrayOfNulls<ImageView>(5)
    var mImage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageType = intent.getStringExtra("choice")
        if (imageType == "cat") {
            mImage = 1
        } else if (imageType == "dog") {
            mImage = 2
        }

        val imageObj01 : ImageView = findViewById(R.id.imageView01)
        val imageObj02 : ImageView = findViewById(R.id.imageView02)
        val catLayout : ConstraintLayout = findViewById(R.id.catLayout)
        val dogLayout : ConstraintLayout = findViewById(R.id.dogLayout)

        if (mImage == 1) {
            imageObj01.setImageResource(R.drawable.cat01)
            imageObj02.setImageResource(R.drawable.cat02)
            for (i in 0..4) {
                checkObj[i] = findViewById(imageviewCatId[i])
            }
            catLayout.isVisible = true
            dogLayout.isVisible = false
        }
        else if (mImage == 2) {
            imageObj01.setImageResource(R.drawable.dog01)
            imageObj02.setImageResource(R.drawable.dog02)
            for (i in 0..4) {
                checkObj[i] = findViewById(imageviewDogId[i])
            }
            catLayout.isVisible = false
            dogLayout.isVisible = true
        }

        var num = 0
        for (i in 0..4) {
            checkObj[i]?.setOnClickListener{
                if (num < 5) {
                    num++
                    checkObj[i]?.setImageResource(R.drawable.ok_select)
                    Toast.makeText(applicationContext, "맞았습니다",
                        Toast.LENGTH_SHORT).show()
                }
                if(num == 5) Toast.makeText(applicationContext, "5개 모두 선택했습니다.",
                    Toast.LENGTH_SHORT).show()
            }
        }

        imageObj01.setOnClickListener{
            Toast.makeText(applicationContext, "틀렸습니다.", Toast.LENGTH_SHORT).show()
        }

        val button : Button = findViewById(R.id.button)
        button.setOnClickListener {
            finish()
        }
    }
}