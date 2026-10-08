package com.example.mp_ex_4_3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var ivobjDress: ImageView
    lateinit var ivobjNecklace: ImageView
    lateinit var ivobjCrown: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivobjDress = findViewById(R.id.ivobjDress)
        ivobjNecklace = findViewById(R.id.ivobjNecklace)
        ivobjCrown = findViewById(R.id.ivobjCrown)

        ivobjCrown.visibility = View.INVISIBLE
        ivobjNecklace.visibility = View.INVISIBLE
        ivobjDress.visibility = View.INVISIBLE

        findViewById<Button>(R.id.btnReset).setOnClickListener {
            ivobjCrown.visibility = View.INVISIBLE
            ivobjNecklace.visibility = View.INVISIBLE
            ivobjDress.visibility = View.INVISIBLE
        }
    }

    fun onClickChoice(view: View) {
        when (view.id) {
            R.id.imageViewDress1 -> {
                ivobjDress.visibility = View.VISIBLE
                ivobjDress.setImageResource(R.drawable.dress1)
            }
            R.id.imageViewDress2 -> {
                ivobjDress.visibility = View.VISIBLE
                ivobjDress.setImageResource(R.drawable.dress2)
            }
            R.id.imageViewDress3 -> {
                ivobjDress.visibility = View.VISIBLE
                ivobjDress.setImageResource(R.drawable.dress3)
            }
            R.id.imageViewCrown1 -> {
                ivobjCrown.visibility = View.VISIBLE
                ivobjCrown.setImageResource(R.drawable.crown1)
            }
            R.id.imageViewCrown2 -> {
                ivobjCrown.visibility = View.VISIBLE
                ivobjCrown.setImageResource(R.drawable.crown2)
            }
            R.id.imageViewNecklace1 -> {
                ivobjNecklace.visibility = View.VISIBLE
                ivobjNecklace.setImageResource(R.drawable.necklace1)
            }
        }
    }
}