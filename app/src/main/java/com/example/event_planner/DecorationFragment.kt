package com.example.event_planner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DecorationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var decorationAdapter: DecorationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_decoration, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewDecoration)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val decorationList = listOf(
            DecorationItem(R.drawable.wedding3, "Floral Decorations"),
            DecorationItem(R.drawable.wedding1, "Stage Decor"),
            DecorationItem(R.drawable.wedding2, "Table Arrangements"),
            DecorationItem(R.drawable.wedding3, "Balloon Decor")
        )

        decorationAdapter = DecorationAdapter(decorationList)
        recyclerView.adapter = decorationAdapter

        return view
    }
}
