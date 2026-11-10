package com.example.mp_ex_7_5

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var m_OkCount : TextView
    lateinit var m_NoCount : TextView
    lateinit var fruitObjA : ImageView
    lateinit var fruitObjB : ImageView
    lateinit var fruitObjC : ImageView

    //과일 이미지 배열
    var fruit = intArrayOf(R.drawable.fruit1, R.drawable.fruit2, R.drawable.fruit3,  R.drawable.fruit4)
    var imageviewId = intArrayOf(R.id.fruit1,  R.id.fruit2,  R.id.fruit3)
    var operaterSign =  arrayOf("+", "-" )
    var operaterId = intArrayOf(R.id.op1, R.id.op2)
    var fruitCount = intArrayOf(3, 6, 8, 1)
    var ivObj = arrayOfNulls<ImageView>(3)
    var opObj = arrayOfNulls<TextView>(2)
    var fruitImage =  IntArray(3)
    var operater =  IntArray(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        val editText : EditText = findViewById(R.id.answer)
        val button1 : Button = findViewById(R.id.button1)
        val button2 : Button = findViewById(R.id.button2)
        Calculate()

        button2.setOnClickListener {
            var result = fruitCount[fruitImage[0]]
            for (i in 0..1) {
                if (operater[i]==0) result += fruitCount[fruitImage[i+1]]
                else result -= fruitCount[fruitImage[i+1]]
            }
            val answer = editText.text.toString()
            if (answer=="") Toast.makeText(applicationContext, "입력해주세요", Toast.LENGTH_SHORT).show()
            else   if (answer.toInt()==result) {
                Toast.makeText(applicationContext, "맞았습니다", Toast.LENGTH_SHORT).show()
                val num = (Integer.parseInt(m_OkCount.text.toString())+1)
                m_OkCount.text =  num.toString()
            }
            else {
                Toast.makeText(applicationContext, "틀렸습니다", Toast.LENGTH_SHORT).show()
                val num2 = (Integer.parseInt(m_NoCount.text.toString())+1)
                m_NoCount.text =  num2.toString()
            }
        }

        button1.setOnClickListener {
            editText.text.clear()
            Calculate()
        }
    }

    fun Calculate(){
        //과일 이미지 초기화
        for (i in 0..2) {
            fruitImage[i] = i
        }

        for (i in 0..2) {
            var randNum= (Math.random() * 3).toInt()
            var swap = fruitImage[randNum]
            fruitImage[randNum] = fruitImage[i]
            fruitImage[i] = swap
        }

        for (i in 0..2) {
            ivObj[i] = findViewById(imageviewId[i])
            var index = fruitImage[i]
            ivObj[i]?.setImageResource(fruit[index])
        }

        for (i in 0..1) {
            var randNum= (Math.random() * 2).toInt()
            operater[i] = randNum
        }

        for (i in 0..1) {
            opObj[i] = findViewById(operaterId[i])
            var index = operater[i]
            opObj[i]?.text = operaterSign[index]
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val item_ok = menu?.findItem(R.id.menu_up)
        item_ok?.setActionView(R.layout.yes_layout )
        val oklayout  = item_ok?.actionView

        if (oklayout != null) {
            m_OkCount = oklayout.findViewById(R.id.thumbs_up_num)
        }

        val item_no = menu?.findItem(R.id.menu_down)
        item_no?.setActionView(R.layout.no_layout )
        val nolayout  = item_no?.actionView

        if (nolayout != null) {
            m_NoCount = nolayout.findViewById(R.id.thumbs_up_num)
        }
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_init -> {
                m_OkCount.text="0"
                m_NoCount.text = "0"
                return true
            }

            R.id.menu_close -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}