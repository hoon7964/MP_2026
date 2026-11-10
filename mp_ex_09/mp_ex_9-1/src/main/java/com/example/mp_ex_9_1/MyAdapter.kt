package com.example.mp_ex_9_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(val itemList : ArrayList<MyItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }
    override fun getItem(p0: Int): Any {
        return itemList[p0]
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val item = this.itemList[p0]
        var convertView = p1
        convertView = LayoutInflater.from(p2?.context).inflate(R.layout.listview_item, p2, false)
        val itemName: TextView = convertView.findViewById(R.id.textView1)
        val itemChar: TextView = convertView.findViewById(R.id.textView2)
        val itemWeight: TextView = convertView.findViewById(R.id.textView3)
        val itemIamge: ImageView = convertView.findViewById(R.id.imageView)
        itemName.text = item.name
        itemChar.text = item.character
        itemWeight.text = item.weight
        itemIamge.setImageResource(item.image)
        return convertView
    }

}