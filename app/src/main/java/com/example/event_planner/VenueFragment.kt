package com.example.event_planner

import android.content.Intent
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
            VenueItem(R.drawable.party, "Party Plot"),
            VenueItem(R.drawable.pool, "Pools"),
            VenueItem(R.drawable.garden, "Garden"),
            )

        venueAdapter = VenueAdapter(venueList) { item ->
            when (item.name) {
                "Party Plot" -> {
                    val intent = Intent(requireContext(), VenueOneActivity::class.java)
                    startActivity(intent)
                }
                // Add more venue clicks here if needed
                "Pools" -> {
                    val intent = Intent(requireContext(), VenueTwoActivity::class.java)
                    startActivity(intent)
                }
                "Garden" -> {
                    val intent = Intent(requireContext(), VenueThreeActivity::class.java)
                    startActivity(intent)
                }

            }
        }

        recyclerView.adapter = venueAdapter

        return view
    }
}
