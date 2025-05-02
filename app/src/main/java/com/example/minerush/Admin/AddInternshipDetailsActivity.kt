package com.example.minerush.Admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityAddInternshipDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddInternshipDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddInternshipDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddInternshipDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }

        binding.addBTN.setOnClickListener {
            val title = binding.internshipTitleET.text.toString().trim()
            val company = binding.companyNameET.text.toString().trim()
            val location = binding.locationET.text.toString().trim()
            val duration = binding.tentureET.text.toString().trim()
            val description = binding.descriptionET.text.toString().trim()
            val requirements = binding.requirementsET.text.toString().trim()
            val stipend = binding.stipendAmountET.text.toString().trim()
            val applicationProcess = binding.applicationProcessET.text.toString().trim()
            val learningOutcomes = binding.learningOutcomesET.text.toString().trim()
            val contactInfo = binding.contactInformationET.text.toString().trim()
            val mode = binding.modeET.text.toString().trim()

            if (listOf(title, company, location, duration, description, requirements, stipend, applicationProcess, learningOutcomes, contactInfo, mode).any { it.isEmpty() }) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val adminEmail = "dev@gmail.com"
            val call = RetrofitClient.instance.addInternship(
                adminEmail,
                title,
                company,
                location,
                duration,
                description,
                requirements,
                stipend,
                applicationProcess,
                learningOutcomes,
                contactInfo,
                mode
            )

            call.enqueue(object : Callback<Map<String, Any>> {
                override fun onResponse(call: Call<Map<String, Any>>, response: Response<Map<String, Any>>) {
                    if (response.isSuccessful && response.body() != null) {
                        val responseBody = response.body()!!
                        val status = responseBody["status"]
                        val message = responseBody["message"]?.toString() ?: "No message returned"

                        if (status == 201.0 || status == 201 || status.toString() == "201") {
                            Toast.makeText(this@AddInternshipDetailsActivity, "Internship added successfully", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@AddInternshipDetailsActivity, "Failed: $message", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(this@AddInternshipDetailsActivity, "Server error: ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                    Toast.makeText(this@AddInternshipDetailsActivity, "Network error: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
