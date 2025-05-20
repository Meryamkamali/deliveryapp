package com.example.deliveryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TimelineAdapter(
    private val items: List<TimelineItem>,
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cbSelection: CheckBox = view.findViewById(R.id.cb_selection)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvTime: TextView = view.findViewById(R.id.tv_time)

        init {
            itemView.setOnClickListener {
                onItemSelected(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timeline, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            cbSelection.isChecked = item.isSelected
            tvTitle.text = item.title
            tvTime.text = item.time
            cbSelection.setOnCheckedChangeListener { _, isChecked ->
                items[position].isSelected = isChecked
            }
        }
    }

    override fun getItemCount() = items.size
}