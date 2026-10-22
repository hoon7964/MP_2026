package com.example.mp_ex_6_3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var launcher: ActivityResultLauncher<Intent>

    val drawableId = intArrayOf(
        R.drawable.shape01, R.drawable.shape02, R.drawable.shape03,
        R.drawable.shape04, R.drawable.shape05, R.drawable.shape06,
        R.drawable.shape07, R.drawable.shape08, R.drawable.shape09
    )
    val imageviewId = intArrayOf(
        R.id.image01, R.id.image02, R.id.image03
    )
    lateinit var rdObj1: RadioButton
    lateinit var rdObj2: RadioButton

    var ivObj : Array<ImageView?> = arrayOfNulls<ImageView>(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rdObj1 = findViewById(R.id.radioButton1)
        rdObj2 = findViewById(R.id.radioButton2)
        for (i in 0..2)
            ivObj[i] = findViewById(imageviewId[i])
        val btnObj : Button = findViewById(R.id.button)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                var resId = result.data?.getIntArrayExtra("resId") ?: IntArray(9)

                var num = 0
                for (i in 0..8) {
                    if (resId[i] >= 1) {
                        ivObj[num]?.setImageResource(drawableId[i])
                        num++
                    }
                }
                var flag = true
                for (i in 0..8) {
                    // 원을 선택한 경우
                    if (rdObj1.isChecked) {
                        if (resId[i] == 1 && i > 2) flag = false
                    }
                    else if(rdObj2.isChecked) {
                        if  ((resId[i] == 1 && i <= 2) || (resId[i] == 1 && i >= 6)) flag = false
                    }
                }
                val layoutResultOjb : LinearLayout = findViewById(R.id.layoutResult)
                layoutResultOjb.setVisibility(View.VISIBLE)
                val tvObj : TextView = findViewById(R.id.textViewResult)
                tvObj.setVisibility(View.VISIBLE)
                if (flag) tvObj.setText("맞았습니다")
                else tvObj.setText("틀렸습니다")
            }
        }

        btnObj.setOnClickListener {
            val intent = Intent(applicationContext, GameActivity::class.java)

            if (rdObj1.isChecked == true)
                intent.putExtra("shape", 1)
            else if (rdObj2.isChecked == true)
                intent.putExtra("shape", 2)

            launcher.launch(intent)
        }
    }
}