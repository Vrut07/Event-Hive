package com.example.event_planner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FlowersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var flowersAdapter: FlowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_flowers, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewFlowers)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val flowerList = listOf(
            FlowerItem(R.drawable.wedding3, "Roses"),
            FlowerItem(R.drawable.wedding2, "Lilies"),
            FlowerItem(R.drawable.wedding1, "Tulips")
        )

        flowersAdapter = FlowersAdapter(flowerList)
        recyclerView.adapter = flowersAdapter

        return view
    }
}
