package com.example.event_planner

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.event_planner.databinding.ActivityRegisterBinding
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val username = binding.edtUser.text.toString().trim()
        val mobile = binding.edtMobile.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        val confirmPassword = binding.edtConfirmPassword.text.toString().trim()

        when {
            username.isEmpty() -> showToast("Enter your username")
            mobile.isEmpty() || mobile.length != 10 -> showToast("Enter a valid 10-digit mobile number")
            password.isEmpty() -> showToast("Enter your password")
            confirmPassword.isEmpty() -> showToast("Confirm your password")
            password != confirmPassword -> showToast("Passwords do not match")
            else -> {
                saveUserToFirebase(username, mobile, password)
            }
        }
    }

    private fun saveUserToFirebase(username: String, mobile: String, password: String) {
        val database = FirebaseDatabase.getInstance().getReference("Users")
        val user = User(username, mobile, password)

        database.child(mobile).setValue(user)
            .addOnSuccessListener {
                showToast("Registration successful!")
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                showToast("Failed to register. Try again.")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
