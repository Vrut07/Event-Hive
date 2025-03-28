package com.example.event_planner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.event_planner.databinding.ActivityHomeBinding
import com.example.eventplanner.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set HomeFragment as the default
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Handle bottom navigation item selection
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())  // Home (Center)
                R.id.nav_profile -> replaceFragment(ProfileFragment()) // Left
                R.id.nav_settings -> replaceFragment(SettingFragment()) // Right
            }
            true
        }

        // Set Home as selected by default
        binding.bottomNavigationView.selectedItemId = R.id.nav_home
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
