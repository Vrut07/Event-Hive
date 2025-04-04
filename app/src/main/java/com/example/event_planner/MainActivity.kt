package com.example.event_planner

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.event_planner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // Navigate to Register Screen
        mainBinding.txtSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Sign In Button Click with Validation
        mainBinding.btnSignin.setOnClickListener {
            if (validateInput()) {
                // If both fields are valid, proceed to HomeActivity
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }

    private fun validateInput(): Boolean {
        val emailOrMobile = mainBinding.txtEmail.text.toString().trim()
        val password = mainBinding.txtPassword.text.toString().trim()

        var isValid = true  // Flag to check validation status

        // Validate Email or Mobile
        if (TextUtils.isEmpty(emailOrMobile)) {
            mainBinding.txtEmail.error = "Enter Email or Mobile Number"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailOrMobile).matches() && !emailOrMobile.matches(Regex("^[0-9]{10}$"))) {
            mainBinding.txtEmail.error = "Enter a valid Email or 10-digit Mobile Number"
            isValid = false
        }

        // Validate Password
        if (TextUtils.isEmpty(password)) {
            mainBinding.txtPassword.error = "Enter Password"
            isValid = false
        } else if (password.length < 6) {
            mainBinding.txtPassword.error = "Password must be at least 6 characters"
            isValid = false
        }

        return isValid  // Only return true if both fields are valid
    }
}
