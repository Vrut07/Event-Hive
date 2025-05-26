package com.example.event_planner

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.event_planner.databinding.ActivityAccountBinding
import com.google.firebase.database.FirebaseDatabase

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var mobile: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mobile = intent.getStringExtra("mobile") ?: ""

        if (mobile.isEmpty()) {
            Toast.makeText(this, "User mobile number not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        loadUserData()
    }

    private fun loadUserData() {
        val ref = FirebaseDatabase.getInstance().reference.child("Users").child(mobile)
        ref.get().addOnSuccessListener { snapshot ->
            val name = snapshot.child("name").value?.toString()
            val email = snapshot.child("email").value?.toString()
            val imageUrl = snapshot.child("imageUrl").value?.toString()

            val details = "Name: $name\nEmail: $email\nMobile: $mobile"
            binding.textAccountDetails.text = details

            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(this).load(imageUrl).into(binding.imageAccountProfile)
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to load user data", Toast.LENGTH_SHORT).show()
        }
    }
}
