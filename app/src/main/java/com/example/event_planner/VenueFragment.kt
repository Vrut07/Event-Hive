package com.example.event_planner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class VenueFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var venueAdapter: VenueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_venue, container, false)

        recyclerView = view.findViewById(R.id.venueRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val venueList = listOf(
            VenueItem(R.drawable.wedding1, "Grand Ballroom", "Ahmedabad"),
            VenueItem(R.drawable.wedding2, "Royal Palace", "Surat"),
            VenueItem(R.drawable.wedding3, "Ocean View Resort", "Goa"),
            VenueItem(R.drawable.wedding3, "Ocean View Resort", "Goa")

        )

        venueAdapter = VenueAdapter(venueList)
        recyclerView.adapter = venueAdapter

        return view
    }
}
