package com.example.minerush.User

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.GlossaryAdaptor
import com.example.minerush.DataClass.GlossaryData
import com.example.minerush.DataClass.GlossaryResponse
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityGlossaryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlossaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlossaryBinding
    private val glossaryList = ArrayList<GlossaryData>()
    private lateinit var glossaryAdapter: GlossaryAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlossaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        setupRecyclerView()
        fetchGlossaryFromApi()
    }

    private fun setupListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        glossaryAdapter = GlossaryAdaptor(glossaryList)
        binding.GlossaryRV.apply {
            layoutManager = LinearLayoutManager(this@GlossaryActivity)
            adapter = glossaryAdapter
        }
    }

    private fun fetchGlossaryFromApi() {
        showLoading(true)

        RetrofitClient.instance.getGlossary().enqueue(object : Callback<GlossaryResponse> {
            override fun onResponse(call: Call<GlossaryResponse>, response: Response<GlossaryResponse>) {
                showLoading(false)
                if (response.isSuccessful) {
                    response.body()?.data?.let { data ->
                        glossaryList.clear()
                        glossaryList.addAll(data)
                        glossaryAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@GlossaryActivity, "Failed to load glossary: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GlossaryResponse>, t: Throwable) {
                showLoading(false)
                Toast.makeText(this@GlossaryActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }
}
