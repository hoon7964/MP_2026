package com.example.mp_ex_10_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val findFromTxt : TextView = findViewById(R.id.findFromTxt)
        val findToTxt : TextView= findViewById(R.id.findToTxt)
        val insertBtn : Button = findViewById(R.id.button_insert)
        val searchBtn : Button= findViewById(R.id.button_search)

        searchBtn.setOnClickListener {
            val builder = MaterialDatePicker.Builder.dateRangePicker()
            val picker = builder.build()
            picker.addOnPositiveButtonClickListener {
                val fromDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.first)
                val toDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.second)
                findFromTxt.text = fromDate
                findToTxt.text = toDate
                val dbHelper = DBHelper(this)
                val list = dbHelper.findTime(fromDate, toDate)
                val FindRecyclerView: RecyclerView = findViewById(R.id.FindRecyclerView)
                val mAdapter = RecyclerViewAdapter(this, list)
                FindRecyclerView.adapter = mAdapter
                val layout = LinearLayoutManager(this)
                FindRecyclerView.layoutManager = layout
                FindRecyclerView.setHasFixedSize(true)
            }
            picker.show(supportFragmentManager, picker.toString())
        }

        insertBtn.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }
    }
}