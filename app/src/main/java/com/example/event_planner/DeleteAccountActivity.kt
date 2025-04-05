package com.example.event_planner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeleteAccountActivity : AppCompatActivity() {

    private lateinit var btnConfirmDelete: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        btnConfirmDelete = findViewById(R.id.btnConfirmDelete)
        btnCancel = findViewById(R.id.btnCancel)

        btnConfirmDelete.setOnClickListener {
            // TODO: Add your account deletion logic here
            Toast.makeText(this, "Account Deleted Successfully", Toast.LENGTH_LONG).show()

            // Redirect to login or splash screen
            val intent = Intent(this, SplashScreenActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        btnCancel.setOnClickListener {
            finish() // just close this activity
        }
    }
}
