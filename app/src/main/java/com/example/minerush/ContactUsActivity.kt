package com.example.minerush

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityContactUsBinding
import com.example.minerush.databinding.ActivitySignInBinding

class ContactUsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContactUsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        }
    }