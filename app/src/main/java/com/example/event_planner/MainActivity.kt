package com.example.event_planner

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.event_planner.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- ADDITION: Auto-login redirect ---
        val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
        val savedMobile = prefs.getString("mobile", null)
        if (!savedMobile.isNullOrEmpty()) {
            // User already logged in, redirect to HomeActivity
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return
        }
        // --- END ADDITION ---

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().getReference("Users")

        // Go to Register screen
        binding.txtSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Sign In logic
        binding.btnSignin.setOnClickListener {
            val mobile = binding.txtEmail.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()

            if (validateInput(mobile, password)) {
                loginUser(mobile, password)
            }
        }
    }

    private fun validateInput(mobile: String, password: String): Boolean {
        if (TextUtils.isEmpty(mobile)) {
            binding.txtEmail.error = "Enter Mobile Number"
            return false
        }
        if (!mobile.matches(Regex("^[0-9]{10}$"))) {
            binding.txtEmail.error = "Enter a valid 10-digit Mobile Number"
            return false
        }
        if (TextUtils.isEmpty(password)) {
            binding.txtPassword.error = "Enter Password"
            return false
        }
        return true
    }

    private fun loginUser(mobile: String, password: String) {
        database.child(mobile).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val dbPassword = snapshot.child("password").value.toString()
                    if (dbPassword == password) {
                        // ✅ Save mobile number to SharedPreferences
                        val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
                        prefs.edit().putString("mobile", mobile).apply()

                        Toast.makeText(this@MainActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Incorrect password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "User not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
