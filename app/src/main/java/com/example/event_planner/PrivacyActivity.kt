package com.example.event_planner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.event_planner.R

class PrivacyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

        // Set title for the activity
        supportActionBar?.title = "Privacy Policy"
    }
}
