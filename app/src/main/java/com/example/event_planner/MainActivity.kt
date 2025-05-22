package com.example.event_planner

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.event_planner.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // Firebase reference
        database = FirebaseDatabase.getInstance().reference.child("Users")

        // Go to Register screen
        mainBinding.txtSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Sign In Logic
        mainBinding.btnSignin.setOnClickListener {
            val mobile = mainBinding.txtEmail.text.toString().trim()
            val password = mainBinding.txtPassword.text.toString().trim()

            if (validateInput(mobile, password)) {
                loginUser(mobile, password)
            }
        }
    }

    private fun validateInput(mobile: String, password: String): Boolean {
        if (TextUtils.isEmpty(mobile)) {
            mainBinding.txtEmail.error = "Enter Mobile Number"
            return false
        }
        if (!mobile.matches(Regex("^[0-9]{10}$"))) {
            mainBinding.txtEmail.error = "Enter a valid 10-digit Mobile Number"
            return false
        }

        if (TextUtils.isEmpty(password)) {
            mainBinding.txtPassword.error = "Enter Password"
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
                        val prefs = getSharedPreferences("UserData", MODE_PRIVATE)
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
