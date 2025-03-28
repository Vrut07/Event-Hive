package com.example.eventplanner

import HomeItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event_planner.R

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val itemList = listOf(
            HomeItem(R.drawable.wedding2, "Venue"),
            HomeItem(R.drawable.wedding1, "Decoration Bookings"),
            HomeItem(R.drawable.wedding3, "Flowers And Bouqets"),
            //HomeItem(R.drawable.photography, "Photography")
        )

        homeAdapter = HomeAdapter(itemList)
        recyclerView.adapter = homeAdapter

        return view
    }
}
