package com.example.event_planner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VenueAdapter(
    private val venueList: List<VenueItem>,
    private val onItemClick: (VenueItem) -> Unit // This is new
) : RecyclerView.Adapter<VenueAdapter.VenueViewHolder>() {

    inner class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.venueName)
        val location = itemView.findViewById<TextView>(R.id.venueLocation)
        val image = itemView.findViewById<ImageView>(R.id.venueImage)

        init {
            itemView.setOnClickListener {
                onItemClick(venueList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_venue, parent, false)
        return VenueViewHolder(view)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val item = venueList[position]
        holder.title.text = item.name
        holder.location.text = item.location
        holder.image.setImageResource(item.imageResId)
    }

    override fun getItemCount(): Int = venueList.size
}
