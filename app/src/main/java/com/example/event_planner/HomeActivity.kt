package com.example.event_planner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.event_planner.databinding.ActivityHomeBinding
//hi

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        homeBinding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Profile())
                R.id.history -> replaceFragment(History())
            }
            true // Explicitly return true to indicate selection was handled
        }



        // Optimized home list creation
        val homeList = arrayListOf(
            HomeData(R.drawable.wedding, "Place And Venue Bookings"),
            HomeData(R.drawable.marraige, "Decoration Bookings"),
            HomeData(R.drawable.flower, "Flowers And Bouquets")
        )

        // Setup RecyclerView
        homeAdapter = HomeAdapter(homeList)
        homeBinding.rvHome.layoutManager = LinearLayoutManager(this)
        homeBinding.rvHome.adapter = homeAdapter
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
