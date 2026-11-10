package com.example.mp_ex_9_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(val catsList : ArrayList<MyItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return catsList.size
    }
    override fun getItem(p0: Int): Any {
        return catsList[p0]
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val cat = this.catsList[p0]
        var convertView = LayoutInflater.from(p2?.context).inflate(R.layout.spinner_item, p2, false)
        val imgCat: ImageView = convertView.findViewById(R.id.imgCat)
        imgCat.setImageResource(cat.image)
        val tvName: TextView = convertView.findViewById(R.id.tvName)
        tvName.text = cat.name
        return convertView
    }
}