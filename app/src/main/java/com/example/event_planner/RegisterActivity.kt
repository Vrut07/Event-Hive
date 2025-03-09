package com.example.event_planner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.event_planner.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.checkTermsCondition.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                registerBinding.btnSignup.visibility = View.VISIBLE
            }else{
                registerBinding.btnSignup.visibility = View.GONE
            }
        }

        registerBinding.rgGender.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rbMale)
            {
                Toast.makeText(this,"Male",Toast.LENGTH_LONG).show()
            }
            else if(checkedId == R.id.rbFemale)
            {
                Toast.makeText(this,"Female",Toast.LENGTH_LONG).show()
            }
        }
        registerBinding.btnSignup.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }

}