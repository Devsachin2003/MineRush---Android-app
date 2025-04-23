package com.example.minerush.Admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityApplicantDetailsForRecentInternApplicationsBinding
import com.example.minerush.databinding.ActivityUserDetailsForRecentlyRegisteredUserBinding

class ApplicantDetailsForRecentInternApplicationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApplicantDetailsForRecentInternApplicationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityApplicantDetailsForRecentInternApplicationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()
    }
    private fun setUpListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}