package com.example.event_planner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.event_planner.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        var homeList = ArrayList<HomeData>()
        var homeList1 = ArrayList<HomeData>()

        homeList1.add(HomeData(R.drawable.wedding,"Place And Venue Bookings"))
        homeList1.add(HomeData(R.drawable.marraige,"Decoration Bookings"))
        homeList1.add(HomeData(R.drawable.flower,"Flowers And Bouquets"))


        homeList.addAll(homeList1)

        homeAdapter = HomeAdapter(homeList)
        homeBinding.rvHome.layoutManager = LinearLayoutManager(this)
        homeBinding.rvHome.adapter = homeAdapter

    }
}













