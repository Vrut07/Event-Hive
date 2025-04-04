package com.example.event_planner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VenueAdapter(private val venueList: List<VenueItem>) :
    RecyclerView.Adapter<VenueAdapter.VenueViewHolder>() {

    inner class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.venueImage)
        private val nameView: TextView = itemView.findViewById(R.id.venueName)
        private val locationView: TextView = itemView.findViewById(R.id.venueLocation)

        fun bind(venue: VenueItem) {
            imageView.setImageResource(venue.imageResId)
            nameView.text = venue.name
            locationView.text = venue.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venue, parent, false)
        return VenueViewHolder(view)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        holder.bind(venueList[position])
    }

    override fun getItemCount(): Int = venueList.size
}
