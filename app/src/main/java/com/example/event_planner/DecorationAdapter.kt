package com.example.event_planner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DecorationAdapter(private val itemList: List<DecorationItem>) :
    RecyclerView.Adapter<DecorationAdapter.DecorationViewHolder>() {

    class DecorationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DecorationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_decoration, parent, false)
        return DecorationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DecorationViewHolder, position: Int) {
        val item = itemList[position]
        holder.imageView.setImageResource(item.imageRes)
        holder.textView.text = item.title
    }

    override fun getItemCount(): Int = itemList.size
}
