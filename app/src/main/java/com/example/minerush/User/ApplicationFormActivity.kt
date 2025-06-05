package com.example.minerush.User

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.api.RetrofitClient
import com.example.minerush.api.serverresponse.ApiResponse
import com.example.minerush.databinding.ActivityApplicationFormBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class ApplicationFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApplicationFormBinding
    private var selectedPdfUri: Uri? = null

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

        binding.sopUploadBTN.setOnClickListener {
            pickPdfLauncher.launch("application/pdf")
        }

        binding.applyBTN.setOnClickListener {
            val name = binding.nameET.text.toString().trim()
            val gender = binding.genderET.text.toString().trim()
            val dob = binding.dobET.text.toString().trim()
            val phone = binding.phoneET.text.toString().trim()
            val email = binding.emailET.text.toString().trim()
            val address = binding.addressET.text.toString().trim()
            val branch = binding.branchET.text.toString().trim()
            val year = binding.yearOfStudyET.text.toString().trim()
            val skills = binding.skillsET.text.toString().trim()
            val company = binding.companyET.text.toString().trim()
            val company_email = binding.companyMailET.text.toString().trim()

            if (selectedPdfUri == null) {
                Toast.makeText(this, "Please upload your resume", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val resumeFile = convertUriToFile(selectedPdfUri!!)
            val resumeRequestBody = resumeFile.asRequestBody("application/pdf".toMediaTypeOrNull())
            val resumePart = MultipartBody.Part.createFormData("resume", resumeFile.name, resumeRequestBody)

            fun part(value: String): RequestBody =
                value.toRequestBody("text/plain".toMediaTypeOrNull())

            val call = RetrofitClient.instance.submitApplication(
                part(name), part(gender), part(dob), part(phone),
                part(email), part(address), part(branch), part(year),
                part(company),part(company_email), part(skills), resumePart
            )

            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        Toast.makeText(this@ApplicationFormActivity, "Application submitted!", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        val errorMsg = response.body()?.message ?: "Unknown error"
                        Toast.makeText(this@ApplicationFormActivity, "Submission failed: $errorMsg", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@ApplicationFormActivity, "Network error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun convertUriToFile(uri: Uri): File {
        val inputStream = contentResolver.openInputStream(uri)!!
        val file = File(cacheDir, "resume_${System.currentTimeMillis()}.pdf")
        FileOutputStream(file).use { outputStream ->
            inputStream.copyTo(outputStream)
        }
        return file
    }
}
