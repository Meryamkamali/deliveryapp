package com.example.deliveryapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(
    private val items: List<MenuItem>,
    private val onItemClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.view.apply {
            findViewById<TextView>(R.id.tv_title).text = item.title
            findViewById<ImageView>(R.id.iv_icon).setImageResource(item.iconRes)
            findViewById<CardView>(R.id.card_view).setCardBackgroundColor(Color.parseColor(item.color))

            setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount() = items.size
}