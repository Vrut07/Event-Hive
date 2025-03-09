package com.example.event_planner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(var homeList:ArrayList<HomeData>):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){
    class HomeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var imgView = itemView.findViewById<ImageView>(R.id.imageView)
        var venueName = itemView.findViewById<TextView>(R.id.txtVenue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_design,parent,false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.imgView.setImageResource(homeList[position].homeImg)
        holder.venueName.text=homeList[position].venue
    }
}