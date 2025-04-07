package com.example.event_planner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlowersAdapter(
    private val flowerList: List<FlowerItem>,
    private val onItemClick: (FlowerItem) -> Unit
) : RecyclerView.Adapter<FlowersAdapter.FlowerViewHolder>() {

    inner class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener {
                onItemClick(flowerList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flower, parent, false)
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val item = flowerList[position]
        holder.image.setImageResource(item.imageResId)
        holder.title.text = item.title
    }

    override fun getItemCount(): Int = flowerList.size
}
