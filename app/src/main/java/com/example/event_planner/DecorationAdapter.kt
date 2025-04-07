package com.example.event_planner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DecorationAdapter(
    private val itemList: List<DecorationItem>,
    private val onItemClick: (DecorationItem) -> Unit
) : RecyclerView.Adapter<DecorationAdapter.DecorationViewHolder>() {

    inner class DecorationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener {
                onItemClick(itemList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DecorationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_decoration, parent, false)
        return DecorationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DecorationViewHolder, position: Int) {
        val item = itemList[position]
        holder.imageView.setImageResource(item.imageRes)
        holder.titleView.text = item.title
    }

    override fun getItemCount(): Int = itemList.size
}
