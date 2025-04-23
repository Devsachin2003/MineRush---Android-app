package com.example.minerush.Admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.databinding.ActivityUserDetailsViewActiviityBinding

class UserDetailsViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsViewActiviityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserDetailsViewActiviityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}
