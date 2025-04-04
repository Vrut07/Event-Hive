package com.example.event_planner

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.event_planner.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        registerBinding.btnSignup.setOnClickListener {
            validateAndRegister()
        }
    }

    private fun validateAndRegister() {
        val username = registerBinding.edtUser.text.toString().trim()
        val mobile = registerBinding.edtMobile.text.toString().trim()
        val password = registerBinding.edtPassword.text.toString().trim()
        val confirmPassword = registerBinding.edtConfirmPassword.text.toString().trim()

        when {
            username.isEmpty() -> showToast("Enter your username")
            mobile.isEmpty() || mobile.length != 10 -> showToast("Enter a valid 10-digit mobile number")
            password.isEmpty() -> showToast("Enter your password")
            confirmPassword.isEmpty() -> showToast("Confirm your password")
            password != confirmPassword -> showToast("Passwords do not match!")
            else -> {
                // Save user details
                saveUserData(username, password)

                showToast("Registration Successful!")
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }

    private fun saveUserData(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
