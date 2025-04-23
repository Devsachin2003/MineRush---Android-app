package com.example.minerush

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityAboutActivtyBinding
import com.example.minerush.databinding.ActivityLoginBinding

class AboutActivty : AppCompatActivity() {
    private lateinit var binding: ActivityAboutActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAboutActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}