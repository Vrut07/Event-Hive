package com.example.event_planner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.event_planner.databinding.FragmentProfileBinding
import com.google.firebase.database.*

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var mobile: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get user mobile from SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("UserData", 0)
        mobile = sharedPref.getString("mobile", "") ?: ""

        if (mobile.isNotEmpty()) {
            database = FirebaseDatabase.getInstance().reference.child("Users").child(mobile)
            loadUserData()
        }

        // Handle edit profile click
        binding.btnEditProfile.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            intent.putExtra("mobile", mobile)
            startActivity(intent)
        }

        // Handle logout
        binding.logout.setOnClickListener {
            sharedPref.edit().clear().apply()
            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun loadUserData() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("name").value?.toString() ?: "N/A"
                val email = snapshot.child("email").value?.toString() ?: "N/A"
                val imageUrl = snapshot.child("imageUrl").value?.toString()

                binding.profileName.text = name
                binding.profileEmail.text = email

                if (!imageUrl.isNullOrEmpty()) {
                    Glide.with(requireContext()).load(imageUrl).into(binding.profileImage)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to load user data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
