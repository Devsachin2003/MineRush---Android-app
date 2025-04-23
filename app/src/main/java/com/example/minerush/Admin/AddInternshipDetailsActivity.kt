package com.example.minerush.Admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityAddInternshipDetailsBinding
import com.example.minerush.databinding.ActivityLoginBinding

class AddInternshipDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddInternshipDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddInternshipDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.clearBTN.setOnClickListener {
            binding.internshipTitleET.setText("")
            binding.companyNameET.setText("")
            binding.locationET.setText("")
            binding.tentureET.setText("")
            binding.requirementsET.setText("")
            binding.descriptionET.setText("")
            binding.stipendAmountET.setText("")
            binding.applicationProcessET.setText("")
            binding.learningOutcomesET.setText("")
            binding.contactInformationET.setText("")
        }

        binding.addBTN.setOnClickListener {
            finish()
        }

        binding.backIV.setOnClickListener {
            finish()
        }
    }
}