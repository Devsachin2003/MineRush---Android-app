package com.example.minerush.Admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.databinding.ActivityRecentApplicationsApplicantDetailsBinding

class RecentApplicationsApplicantDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentApplicationsApplicantDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecentApplicationsApplicantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        // Set your listeners here
    }
}
