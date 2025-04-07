package com.example.event_planner

import android.content.Intent
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

        decorationAdapter = DecorationAdapter(decorationList){item ->
            when (item.title){
                "Floral Decorations" -> {
                    val intent = Intent(requireContext(),DecorationOneActivity::class.java)
                    startActivity(intent)
                }
                "Stage Decor" -> {
                    val intent = Intent(requireContext(),DecorationTwoActivity::class.java)
                    startActivity(intent)
                }
                "Table Arrangements" -> {
                    val intent = Intent(requireContext(),DecorationThreeActivity::class.java)
                    startActivity(intent)
                }
                "Balloon Decor" -> {
                    val intent = Intent(requireContext(),DecorationfourActivity::class.java)
                    startActivity(intent)
                }
            }

        }
        recyclerView.adapter = decorationAdapter

        return view
    }
}
