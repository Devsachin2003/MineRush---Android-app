package com.example.minerush.User

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.DataClass.RulesData
import com.example.minerush.DataClass.RulesResponse
import com.example.minerush.R
import coil.load
import com.bumptech.glide.Glide
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityEachRulePageBinding
import retrofit2.*


class EachRulePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEachRulePageBinding
    private var ruleId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEachRulePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ruleId = intent.getStringExtra("id") ?: ""

        if (ruleId.isNotEmpty()) {
            fetchRuleDetails(ruleId)
        } else {
            binding.ruleNameTV.text = "Invalid Rule ID"
        }

        setListeners()
    }

    private fun fetchRuleDetails(id: String) {


        val apiService = RetrofitClient.instance.getRule(id)

        apiService.enqueue(object : Callback<RulesResponse> {
            override fun onResponse(call: Call<RulesResponse>, response: Response<RulesResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    displayRuleDetails(response.body()!!.data!!)
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Unknown error"
                    binding.ruleNameTV.text = "Failed to load rule"
                    Log.e("EachRulePageActivity", "Response error: $errorMsg")
                }
            }

            override fun onFailure(call: Call<RulesResponse>, t: Throwable) {
                binding.ruleNameTV.text = "Error: ${t.localizedMessage}"
                Log.e("EachRulePageActivity", "Network Error", t)
            }
        })
    }

    private fun displayRuleDetails(rule: RulesData) {
        binding.ruleNameTV.text = rule.ruleName ?: "No name available"
        binding.descriptionText.text = rule.description


//        rule.image?.let {
//            // Coil will handle loading the image into the ImageView
//            binding.mediaImageView.load(it) {
//                crossfade(true) // Optional: Add a crossfade effect while loading
//                placeholder(R.drawable.placeholder_image) // Optional: Placeholder while loading
//                error(R.drawable.error_image) // Optional: Error image if loading fails
//            }
//        }

        Glide.with(this).load(rule.image).error(R.drawable.indian_explosive_act).into(binding.mediaImageView)


        binding.viewPdfButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(rule.document), "application/pdf")
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

                // Try to open with an external app
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("EachRulePageActivity", "No app found to open PDF", e)

            }
        }

    }

    private fun setListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }
}
