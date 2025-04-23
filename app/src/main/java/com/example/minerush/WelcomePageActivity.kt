package com.example.minerush

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityWelcomePageBinding

class WelcomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTimer()
    }

    private fun setTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }, 3000)
    }
}