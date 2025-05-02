package com.example.minerush.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.InternApprovalAdapter
import com.example.minerush.DataClass.InternApplicant
import com.example.minerush.DataClass.InternApplicantResponse
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityNewInternApplicantBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewInternApplicantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewInternApplicantBinding
    private val applications = ArrayList<InternApplicant>()
    private lateinit var adapter: InternApprovalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNewInternApplicantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBackButton()
        setupRecyclerView()
        fetchApplications()
    }

    private fun setupBackButton() {
        binding.backIV.setOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        adapter = InternApprovalAdapter(applications)
        binding.recentApplicationsRV.layoutManager = LinearLayoutManager(this)
        binding.recentApplicationsRV.adapter = adapter

        adapter.setClickListeners(object : InternApprovalAdapter.OnItemClickListeners {
            override fun onClick(applicant: InternApplicant, position: Int) {
                val intent = Intent(
                    this@NewInternApplicantActivity,
                    ApplicantDetailsForRecentInternApplicationsActivity::class.java
                )
                intent.putExtra("name", applicant.name)
                intent.putExtra("email", applicant.email)
                intent.putExtra("phone", applicant.phone)
                intent.putExtra("degree", applicant.degree) // Fixed
                intent.putExtra("year", applicant.year)   // Fixed
                startActivity(intent)
            }
        })

    }

    private fun fetchApplications() {
        RetrofitClient.instance.getInternApplicantDetails()
            .enqueue(object : Callback<InternApplicantResponse> {
                override fun onResponse(
                    call: Call<InternApplicantResponse>,
                    response: Response<InternApplicantResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val fetchedList = response.body()!!.data
                        applications.clear()
                        applications.addAll(fetchedList)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@NewInternApplicantActivity, "No applications found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<InternApplicantResponse>, t: Throwable) {
                    Toast.makeText(this@NewInternApplicantActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
