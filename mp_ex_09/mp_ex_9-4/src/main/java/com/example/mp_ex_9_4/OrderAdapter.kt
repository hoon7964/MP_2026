package com.example.mp_ex_9_4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(val context: Context, val fruit: ArrayList<Fruit>) :
    RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {
    private lateinit  var selectChangedListener: OrderAdapter.OnSelectChangedListener
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentFruitImage : ImageView = itemView.findViewById(R.id.image)
        val fruitPrice : TextView = itemView.findViewById(R.id.price)
        var totaltCost: TextView = itemView.findViewById(R.id.cost)
        var subtractFruit: TextView = itemView.findViewById(R.id.minus)
        var quantityText: TextView = itemView.findViewById(R.id.quantity)
        var addFruit: TextView = itemView.findViewById(R.id.plus)
        var removeFruit: TextView= itemView.findViewById(R.id.delete_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val  layoutInflater  =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item : Fruit = fruit.get(position)
        holder.currentFruitImage.setImageResource(item.mImage)
        holder.fruitPrice.text = item.mPrice.toString()
        holder.totaltCost.text = " " + item.mPrice * item.mQuantity
        holder.quantityText.text = "x " + item.mQuantity
        holder.addFruit.setOnClickListener {
            item.mQuantity +=1
            holder.quantityText.text = "x " + item.mQuantity
            holder.totaltCost.text = " " +item.mPrice * item.mQuantity
            selectChangedListener.onSelectChanged(fruit)
            notifyDataSetChanged()
        }
        holder.subtractFruit.setOnClickListener{
            //item.removeFromQuantity()
            item.mQuantity -=1
            if( item.mQuantity <0 )  item.mQuantity = 0
            holder.quantityText.text = "x " + item.mQuantity
            holder.totaltCost.text = " " + item.mPrice * item.mQuantity
            selectChangedListener.onSelectChanged(fruit)
            notifyDataSetChanged()
        }
        holder.removeFruit.setOnClickListener {
            fruit.removeAt(position)
            selectChangedListener.onSelectChanged(fruit)
            notifyDataSetChanged()
        }
    }
    override fun getItemCount(): Int {
        return fruit.size;
    }
    interface OnSelectChangedListener {
        fun onSelectChanged(item : ArrayList<Fruit>)
    }
    fun setOnSelectChangedListener(listener: OnSelectChangedListener) {
        selectChangedListener = listener
    }
}