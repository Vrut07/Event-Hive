package com.example.event_planner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide system UI for full-screen mode
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        setContentView(R.layout.activity_splash_screen)

        val videoView = findViewById<VideoView>(R.id.splashVideo)
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.splashscreen}")
        videoView.setVideoURI(videoUri)

        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = false
            mediaPlayer.setVolume(1f, 1f) // Adjust volume if needed
            mediaPlayer.start()

            // Stop video after 1 second and move to MainActivity
            Handler(Looper.getMainLooper()).postDelayed({
                mediaPlayer.pause()  // Pause the video
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1000) // 2000ms = 2 second
        }
    }
}
