package com.example.mp_ex_9_1

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var listview: ListView
    lateinit var adapter : MyAdapter
    var catsList = ArrayList<MyItem>()
    var selectItemPos :Int =-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        catsList.add(MyItem("아비시니안", "활발하고 온순한 성격", "3~5kg", R.drawable.abyssinian))
        catsList.add(MyItem("샴", "놀기 좋아하고 온순한 성격", "3.6~5.4kg", R.drawable.siamese))
        catsList.add(MyItem("앙고라", "예인하고 상냥한 성격", "3.7~7kg", R.drawable.angora))
        catsList.add(MyItem("랙돌", "느긋하고 온순한 성격", "5~9kg", R.drawable.regdol))
        catsList.add(MyItem("뱅갈", "충성심 강하고 상냥한 성격", "5~8kg", R.drawable.bengal))
        catsList.add(MyItem("스핑크스", "활발하고 사교성 좋은 성격", "4~5kg", R.drawable.sphinx))
        catsList.add(MyItem("페르시안", "조용하고 온순한 성격", "3.6~5.4kg", R.drawable.persian))
        val btInsert: Button = findViewById(R.id.button1)
        val btModify: Button = findViewById(R.id.button2)
        val btDelete: Button = findViewById(R.id.button3)
        listview = findViewById(R.id.listview)
        adapter = MyAdapter(catsList)
        listview.adapter = adapter
        listview.setOnItemClickListener { adapterView, view, i, l ->
            val itemValue : MyItem = adapterView?.getItemAtPosition(i)  as MyItem
            selectItemPos = i
            Toast.makeText(
                applicationContext,
                itemValue.name + " " + itemValue.character,
                Toast.LENGTH_SHORT
            ).show()
        }

        btInsert.setOnClickListener {
            val count: Int
            count = adapter.getCount()
            catsList.add(
                MyItem(
                    "러시안블루" + Integer.toString(count + 1),
                    "온순하고 애교 많은 성격",
                    "3.5~5kg",
                    R.drawable.russianblue
                )
            )
            adapter.notifyDataSetChanged()
        }

        btModify.setOnClickListener {
            val count =  adapter.getCount()
            val checked= selectItemPos
            if (checked >= 0 && count >0){
                val alertDialog = AlertDialog.Builder(this)
                val inflater: LayoutInflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.dialog_layout, null)
                alertDialog.setView(dialogView)
                val dialog = alertDialog.show()
                val title: TextView = dialogView.findViewById(R.id.textView)
                title.text = "고양이 : " + catsList.get(checked).name
                val layout_delete: LinearLayout = dialogView.findViewById(R.id.layout_delete)
                val layout_modify: LinearLayout = dialogView.findViewById(R.id.layout_modify)
                layout_delete.isVisible =false
                layout_modify.isVisible = true
                val button_yes: Button = dialogView.findViewById(R.id.button_yes)
                val button_no: Button = dialogView.findViewById(R.id.button_no)
                button_yes.text = "수정"
                button_no.text = "취소"
                button_yes.setOnClickListener {
                    var weight: EditText = dialogView.findViewById(R.id.name)
                    dialog.dismiss()
                    if (checked > -1 && checked < count) {
                        var nameKor: String = catsList.get(checked).name
                        var nameEng: String = catsList.get(checked).character
                        var version: String = "몸무게 " + weight.text.toString() + "kg"
                        var image: Int = catsList.get(checked).image
                        catsList.set(checked, MyItem(nameKor, nameEng, version, image))
                        adapter.notifyDataSetChanged()
                    }
                }
                button_no.setOnClickListener {
                    dialog.dismiss()
                }
            }
            else Toast.makeText(this, "항목을 선택하세요", Toast.LENGTH_SHORT).show()
        }
        btDelete.setOnClickListener {
            val count =  adapter.getCount()
            val checked= selectItemPos
            if (checked >= 0 && count >0) {
                val alertDialog = AlertDialog.Builder(this)
                val inflater: LayoutInflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.dialog_layout, null)
                alertDialog.setView(dialogView)
                val dialog = alertDialog.show()
                val title: TextView = dialogView.findViewById(R.id.textView)
                title.text = "고양이 : " + catsList.get(checked).name
                val layout_delete: LinearLayout = dialogView.findViewById(R.id.layout_delete)
                val layout_modify: LinearLayout = dialogView.findViewById(R.id.layout_modify)
                layout_delete.isVisible =true
                layout_modify.isVisible = false
                val button_yes: Button = dialogView.findViewById(R.id.button_yes)
                val button_no: Button = dialogView.findViewById(R.id.button_no)
                button_yes.text = "예"
                button_no.text = "아니오"
                button_yes.setOnClickListener {
                    var weight: EditText = dialogView.findViewById(R.id.name)
                    dialog.dismiss()
                    if (checked > -1 && checked < count) {
                        catsList.removeAt(checked)
                        listview.clearChoices()
                        adapter.notifyDataSetChanged()
                        dialog.dismiss()
                    }
                }
                button_no.setOnClickListener {
                    dialog.dismiss()
                }
            }
            else Toast.makeText(this, "항목을 선택하세요", Toast.LENGTH_SHORT).show()
        }
    }
}