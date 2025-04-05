package com.example.event_planner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.event_planner.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example account details
        binding.textAccountDetails.text = """
            Name: Disha Vala
            Email: valadisha13@gmail.com
            Phone: 9909665979
            Location: Ahmedabad
        """.trimIndent()
    }
}
