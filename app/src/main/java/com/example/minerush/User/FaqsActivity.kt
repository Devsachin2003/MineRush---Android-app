package com.example.minerush.User

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.FaqsAboutTheChatAdaptor
import com.example.minerush.Adapter.FaqsAdapter
import com.example.minerush.Adapter.SectionAdapter
import com.example.minerush.DataClass.FaqsData
import com.example.minerush.DataClass.FaqsResponse
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityFaqsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FaqsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFaqsBinding

    private lateinit var sectionAdapter: SectionAdapter
    private val sectionList = mutableListOf<Pair<String, List<FaqsData>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        fetchFaqsFromApi()
    }

    private fun setListeners() {
        binding.backIV.setOnClickListener { finish() }
    }

    private fun fetchFaqsFromApi() {
        showLoading(true)

        RetrofitClient.instance.getFaqs().enqueue(object : Callback<FaqsResponse> {
            override fun onResponse(call: Call<FaqsResponse>, response: Response<FaqsResponse>) {
                showLoading(false)
                if (response.isSuccessful) {
                    response.body()?.let { faqResponse ->
                        sectionList.clear()

                        faqResponse.data.basics.let {
                            if (it.isNotEmpty()) {
                                binding.aboutTheChatbotRV.adapter = FaqsAdapter(faqResponse.data.basics)
                            }
                        }
                        faqResponse.data.stakeholders.let {
                            if (it.isNotEmpty()) {
                                binding.stakeholdersAndCustomersRV.adapter = FaqsAdapter(faqResponse.data.stakeholders)
                            }
                        }
                        faqResponse.data.usage.let {
                            if (it.isNotEmpty()) {
                                binding.usageChatbotRV.adapter = FaqsAdapter(faqResponse.data.usage)
                            }
                        }
                    }
                } else {
                    Toast.makeText(this@FaqsActivity, "Failed to load FAQs", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FaqsResponse>, t: Throwable) {
                showLoading(false)
                Toast.makeText(this@FaqsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showLoading(show: Boolean) {
        // Handle progress bar if any
    }
}
