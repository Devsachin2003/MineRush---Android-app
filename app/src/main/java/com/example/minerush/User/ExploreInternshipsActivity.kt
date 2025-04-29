package com.example.minerush.User

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.ExploreInternshipAdaptor
import com.example.minerush.DataClass.ExploreInternData
import com.example.minerush.DataClass.InternshipResponse
import com.example.minerush.R
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityExploreInternshipsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreInternshipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExploreInternshipsBinding
    private var internships = ArrayList<ExploreInternData>()
    private lateinit var adapter: ExploreInternshipAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExploreInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSpinners()
        setupListeners()
        fetchInternshipsFromAPI()
    }

    private fun setupRecyclerView() {
        binding.InternshipRV.layoutManager = LinearLayoutManager(this)
        adapter = ExploreInternshipAdaptor(internships)
        binding.InternshipRV.adapter = adapter

        adapter.setClickListeners(object : ExploreInternshipAdaptor.OnItemClickListeners {
            override fun onClick(article: ExploreInternData, position: Int) {
                val intent = Intent(
                    this@ExploreInternshipsActivity,
                    ApplicationFormActivity::class.java
                )
                startActivity(intent)
            }
        })
    }

    private fun setupSpinners() {
        val internshipTypeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.internship_type,
            android.R.layout.simple_spinner_item
        )
        internshipTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.InternshipTypeDropdownMenu.adapter = internshipTypeAdapter

        binding.InternshipTypeDropdownMenu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // You can filter your internships list based on type if needed
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val locationAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.locations,
            android.R.layout.simple_spinner_item
        )
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.locationDropdownMenu.adapter = locationAdapter

        binding.locationDropdownMenu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // You can filter internships based on location if needed
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }

    private fun fetchInternshipsFromAPI() {
        RetrofitClient.instance.getInternships().enqueue(object : Callback<InternshipResponse> {
            override fun onResponse(call: Call<InternshipResponse>, response: Response<InternshipResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    internships.clear()
                    internships.addAll(response.body()!!.internships)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@ExploreInternshipsActivity, "No internships found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<InternshipResponse>, t: Throwable) {
                Toast.makeText(this@ExploreInternshipsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
