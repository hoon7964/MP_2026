package com.example.mp_ex_9_4

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit  var fruitTotalText: TextView
    lateinit  var   listViewItems: ArrayList<Fruit>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listViewItems = ArrayList<Fruit>()
        listViewItems.add(Fruit(R.drawable.fruit1, 30))
        listViewItems.add(Fruit(R.drawable.fruit2, 40))
        listViewItems.add(Fruit(R.drawable.fruit3, 60))
        listViewItems.add(Fruit(R.drawable.fruit4, 80))
        listViewItems.add(Fruit(R.drawable.fruit5, 100))
        fruitTotalText = findViewById(R.id.total)
        fruitTotalText.text = "0"
        val adapter = OrderAdapter(this, listViewItems)
        val recyclerView: RecyclerView = findViewById(R.id.fruit_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setOnSelectChangedListener(object : OrderAdapter.OnSelectChangedListener {
            override fun onSelectChanged(item: ArrayList<Fruit>) {
                setFruitTotal()
            }
        })
    }
    fun calculateFruitTotal() : Int{
        var fruitTotal = 0;
        for( order in   listViewItems ){
            fruitTotal += order.mPrice * order.mQuantity
        }
        return fruitTotal;
    }
    fun setFruitTotal(){
        fruitTotalText.setText(calculateFruitTotal().toString())
    }
}