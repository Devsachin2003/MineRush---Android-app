package com.example.minerush.Admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityManageUsersBinding
import com.example.minerush.databinding.ActivityUserDetailsForRecentlyRegisteredUserBinding
import com.example.minerush.databinding.ActivityUserDetailsViewActiviityBinding

class UserDetailsForRecentlyRegisteredUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsForRecentlyRegisteredUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_details_for_recently_registered_user)
        binding = ActivityUserDetailsForRecentlyRegisteredUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()

    }
    private fun setUpListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}