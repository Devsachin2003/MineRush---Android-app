package com.example.minerush.Admin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.DataClass.ApprovalResponse
import com.example.minerush.DataClass.CancellationResponse
import com.example.minerush.DataClass.InternApplicantDetails
import com.example.minerush.DataClass.InternApplicantDetailsResponse
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityRecentApplicationsApplicantDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecentApplicationsApplicantDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentApplicationsApplicantDetailsBinding

    private var applicantName: String? = null // To store the applicant's name passed from the previous activity
    private var pdfUrl: String? = null // To store the PDF URL (if any)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecentApplicationsApplicantDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the applicant's name from the intent
        applicantName = intent.getStringExtra("name")
        if (applicantName == null) {
            Toast.makeText(this, "Applicant name is missing.", Toast.LENGTH_SHORT).show()
            finish() // Exit if name is not provided
            return
        }

        setListeners()
        loadApplicantDetails(applicantName!!) // Pass the non-null applicantName to the function
    }

    private fun setListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }

        binding.openPDF.setOnClickListener {
            if (pdfUrl != null) {
                // Open the PDF in a new activity or with a PDF viewer
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf")
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "No PDF viewer found.", Toast.LENGTH_SHORT).show()
                    Log.e("PDF_VIEW", "Error opening PDF: ${e.message}")
                }
            } else {
                Toast.makeText(this, "No PDF available.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.addBTN.setOnClickListener {
            // Implement approve logic here
            updateApplicationStatus(applicantName, "Approved")
        }

        binding.cancelBTN.setOnClickListener {
            // Implement reject logic here
            updateApplicationStatus(applicantName, "Rejected")
        }
    }

    private fun loadApplicantDetails(name: String) {
        // Show loading state
        showLoading()

        // Fetch applicant data from the Flask API via Retrofit
        RetrofitClient.instance.getInternApplicantDetails(name).enqueue(object : Callback<InternApplicantDetailsResponse> {
            override fun onResponse(call: Call<InternApplicantDetailsResponse>, response: Response<InternApplicantDetailsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val internApplicantDetails = response.body()!!.data

                    if (internApplicantDetails != null) {
                        // Update UI with intern data
                        binding.nameValueTV.text = internApplicantDetails.name ?: "N/A"
                        binding.phoneValueTV.text = internApplicantDetails.phone ?: "N/A"
                        binding.emailValueTV.text = internApplicantDetails.email ?: "N/A"
                        binding.branchValueTV.text = internApplicantDetails.degreeBranch ?: "N/A"
                        binding.studyValueTV.text = internApplicantDetails.yearOfStudy
                        binding.skillsValueTV.text = internApplicantDetails.skills ?: "N/A"

                        pdfUrl = internApplicantDetails.resume // Get PDF URL if available

                        // Conditionally show the pdf button
                        if (pdfUrl.isNullOrEmpty()) {
                            binding.openPDF.visibility = View.GONE
                        } else {
                            binding.openPDF.visibility = View.VISIBLE
                        }
                    } else {
                        Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, "Applicant not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
                hideLoading()
            }

            override fun onFailure(call: Call<InternApplicantDetailsResponse>, t: Throwable) {
                Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                hideLoading()
            }
        })
    }

    private fun updateApplicationStatus(name: String?, status: String) {
        if (name == null) {
            Toast.makeText(this, "Applicant name is null.", Toast.LENGTH_SHORT).show()
            return
        }

        // Show a loading indicator if desired before the API call
        // showLoading() // You might want a different loading for this action

        if (status == "Approved") {
            RetrofitClient.instance.approveApplicant(name).enqueue(object : Callback<ApprovalResponse> {
                override fun onResponse(call: Call<ApprovalResponse>, response: Response<ApprovalResponse>) {
                    // hideLoading() // Hide loading after response
                    if (response.isSuccessful && response.body() != null) {
                        val approvalResponse = response.body()!!
                        Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, approvalResponse.message, Toast.LENGTH_SHORT).show()
                        if (approvalResponse.status == "success" && !approvalResponse.gmail_url.isNullOrEmpty()) {
                            // Open Gmail with pre-filled content
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(approvalResponse.gmail_url))
                            try {
                                startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, "No email client found.", Toast.LENGTH_SHORT).show()
                                Log.e("EMAIL_CLIENT", "Error opening email client: ${e.message}")
                            }
                        }
                    } else {
                        Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, "Failed to approve applicant.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApprovalResponse>, t: Throwable) {
                    // hideLoading() // Hide loading after failure
                    Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, "Error approving: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else if (status == "Rejected") {
            RetrofitClient.instance.cancelApplicant(name).enqueue(object : Callback<CancellationResponse> {
                override fun onResponse(call: Call<CancellationResponse>, response: Response<CancellationResponse>) {
                    // hideLoading() // Hide loading after response
                    if (response.isSuccessful && response.body() != null) {
                        val cancellationResponse = response.body()!!
                        Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, cancellationResponse.message, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, "Failed to reject applicant.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CancellationResponse>, t: Throwable) {
                    // hideLoading() // Hide loading after failure
                    Toast.makeText(this@RecentApplicationsApplicantDetailsActivity, "Error rejecting: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun showLoading() {
        // Show progress indicators in the TextViews
        binding.nameValueTV.text = "Loading..."
        binding.phoneValueTV.text = "Loading..."
        binding.emailValueTV.text = "Loading..."
        binding.branchValueTV.text = "Loading..."
        binding.studyValueTV.text = "Loading..."
        binding.skillsValueTV.text = "Loading..."
        binding.openPDF.visibility = View.GONE
        // Consider showing a ProgressBar here for overall loading
    }

    private fun hideLoading() {
        // Clear progress indicators, the actual data will be set by the response
        // If using a ProgressBar, hide it here.
    }
}