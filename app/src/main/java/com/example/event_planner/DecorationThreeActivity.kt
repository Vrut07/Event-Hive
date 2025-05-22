package com.example.event_planner

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DecorationThreeActivity : AppCompatActivity() {
    private var isFav1 = false
    private var isFav2 = false
    private var isFav3 = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_decoration_three)
        val heart1 = findViewById<ImageView>(R.id.heart_button1)
        val heart2 = findViewById<ImageView>(R.id.heart_button2)
        val heart3 = findViewById<ImageView>(R.id.heart_button3)

        heart1.setOnClickListener {
            isFav1 = !isFav1
            heart1.setImageResource(
                if (isFav1) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )
        }

        heart2.setOnClickListener {
            isFav2 = !isFav2
            heart2.setImageResource(
                if (isFav2) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )
        }

        heart3.setOnClickListener {
            isFav3 = !isFav3
            heart3.setImageResource(
                if (isFav3) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )
        }
    }
}



