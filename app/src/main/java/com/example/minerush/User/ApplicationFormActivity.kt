package com.example.minerush.User

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.databinding.ActivityApplicationFormBinding

class ApplicationFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApplicationFormBinding
    private var selectedImageUri: Uri? = null
    private var selectedPdfUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            Toast.makeText(this, "Photo selected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No photo selected", Toast.LENGTH_SHORT).show()
        }
    }

    private val pickPdfLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedPdfUri = uri
            Toast.makeText(this, "Resume PDF selected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No PDF selected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.backCL.setOnClickListener {
            startActivity(Intent(this, ExploreInternshipsActivity::class.java))
        }

        // Upload Photo
        binding.uploadProofBTN.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        // Upload Resume
        binding.sopUploadBTN.setOnClickListener {
            pickPdfLauncher.launch("application/pdf")
        }

        // Apply Button (Example: Collect all data and show a Toast, replace with DB logic)
        binding.clearBTN.setOnClickListener {
            val name = binding.nameET.text.toString()
            val gender = binding.genderET.text.toString()
            val dob = binding.dobET.text.toString()
            val phone = binding.phoneET.text.toString()
            val email = binding.emailET.text.toString()
            val branch = binding.branchET.text.toString()
            val year = binding.yearOfStudyET.text.toString()
            val skills = binding.skillsET.text.toString()

            if (selectedImageUri == null || selectedPdfUri == null) {
                Toast.makeText(this, "Please upload both photo and resume", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Here you would upload this data + files to Firebase or your DB
            Toast.makeText(this, "Application submitted!\nName: $name", Toast.LENGTH_LONG).show()
        }
    }
}
