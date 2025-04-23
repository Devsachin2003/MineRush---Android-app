package com.example.minerush.User

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityDisclaimerBinding
import com.example.minerush.databinding.ActivityExploreInternshipsBinding

class DisclaimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisclaimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDisclaimerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_disclaimer)
        setListeners()
        redirects()

    }

    private fun redirects() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }
    private fun setListeners() {
        binding.backIV.setOnClickListener{
            finish()
        }
    }
}