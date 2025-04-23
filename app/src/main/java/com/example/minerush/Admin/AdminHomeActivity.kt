package com.example.minerush.Admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityAdminHomeBinding
import com.example.minerush.databinding.ActivityContactUsBinding

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAdminHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.internshipCL.setOnClickListener {
            val intent = Intent(this,AddInternshipDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.rulesandactsCL.setOnClickListener {
            val intent = Intent(this,UpdateExistingRuleActivity::class.java)
            startActivity(intent)
        }
        binding.recentlyRegisteredUsersCL.setOnClickListener {
            val intent = Intent(this,RecentlyRegisteredUsersViewDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.viewRecentApplicationsCL.setOnClickListener {
            val intent = Intent(this,NewInternApplicantActivity::class.java)
            startActivity(intent)
        }


        binding.faqsCL.setOnClickListener {
            val intent = Intent(this,AddFaqActivity::class.java)
            startActivity(intent)
        }

        binding.manageusersCL.setOnClickListener {
            val intent = Intent(this,ManageUsersActivity::class.java)
            startActivity(intent)
        }
    }
}