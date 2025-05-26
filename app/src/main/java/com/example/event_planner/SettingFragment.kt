package com.example.event_planner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.event_planner.databinding.FragmentSettingBinding
import com.google.firebase.database.FirebaseDatabase

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get stored mobile number from SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val mobile = sharedPref.getString("mobile", "") ?: ""

        binding.textPrivacy.setOnClickListener {
            startActivity(Intent(requireContext(), PrivacyActivity::class.java))
        }

        binding.textAccount.setOnClickListener {
            val intent = Intent(requireContext(), AccountActivity::class.java)
            intent.putExtra("mobile", mobile)
            startActivity(intent)
        }

        binding.textNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }

        binding.textDeleteAccount.setOnClickListener {
            if (mobile.isNotEmpty()) {
                // Delete user node from Firebase
                val database = FirebaseDatabase.getInstance().getReference("Users")
                database.child(mobile).removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Clear SharedPreferences
                        sharedPref.edit().clear().apply()

                        Toast.makeText(requireContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show()

                        // Redirect to login (MainActivity)
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        Toast.makeText(requireContext(), "Failed to delete account", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Mobile number not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
