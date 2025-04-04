package com.example.eventplanner

import HomeItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event_planner.DecorationFragment
import com.example.event_planner.FlowersFragment
import com.example.event_planner.R
import com.example.event_planner.VenueFragment

class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    private val homeItemList = listOf(
        HomeItem(R.drawable.wedding2, "Venue"),
        HomeItem(R.drawable.wedding1, "Decoration Bookings"),
        HomeItem(R.drawable.wedding3, "Flowers")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        homeAdapter = HomeAdapter(homeItemList) { item ->
            when (item.title) {
                "Venue" -> navigateToFragment(VenueFragment())
                "Decoration Bookings" -> navigateToFragment(DecorationFragment())
                "Flowers" -> navigateToFragment(FlowersFragment())
            }
        }

        recyclerView.adapter = homeAdapter
        return view
    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
