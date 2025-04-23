package com.example.minerush.User

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityDisclaimerBinding
import com.example.minerush.databinding.ActivityEditUserProfileBinding

class EditUserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditUserProfileBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_edit_user_profile)

    }
}