package com.example.minerush.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minerush.DataClass.RecentData
import com.example.minerush.DataClass.InternApplicantResponse
import com.example.minerush.DataClass.RecentUsersResponse
import com.example.minerush.adapters.RecentUserAdapter
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityRecentlyRegisteredUsersViewDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecentlyRegisteredUsersViewDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecentlyRegisteredUsersViewDetailsBinding
    private val applications = ArrayList<RecentData>()
    private lateinit var adapter: RecentUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecentlyRegisteredUsersViewDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBackButton()
        setupRecyclerView()
        fetchApplications()
    }

    private fun setupBackButton() {
        binding.backIV.setOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        adapter = RecentUserAdapter(applications)
        binding.recentUsersRV.layoutManager = LinearLayoutManager(this)
        binding.recentUsersRV.adapter = adapter

        adapter.setClickListeners(object : RecentUserAdapter.OnItemClickListeners {
            override fun onClick(applicant: RecentData, position: Int) {
                val intent = Intent(
                    this@RecentlyRegisteredUsersViewDetailsActivity,
                    ApplicantDetailsForRecentInternApplicationsActivity::class.java
                )
                intent.putExtra("name", applicant.name)
                intent.putExtra("email", applicant.email)
                intent.putExtra("phone", applicant.phone)
                intent.putExtra("role", applicant.role)
                startActivity(intent)
            }
        })
    }

    private fun fetchApplications() {
        RetrofitClient.instance.getRecentUsers()
            .enqueue(object : Callback<RecentUsersResponse> {
                override fun onResponse(
                    call: Call<RecentUsersResponse>,
                    response: Response<RecentUsersResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val fetchedList = response.body()!!.data
                        applications.clear()
                        applications.addAll(fetchedList)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(
                            this@RecentlyRegisteredUsersViewDetailsActivity,
                            "No applications found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<RecentUsersResponse>, t: Throwable) {
                    Toast.makeText(
                        this@RecentlyRegisteredUsersViewDetailsActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
