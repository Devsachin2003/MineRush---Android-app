package com.example.minerush.Admin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.R
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityAddFaqBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFaqActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFaqBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setListeners()
    }

    private fun setListeners() {
        binding.backIV.setOnClickListener {
            finish()
        }

        binding.addFaqBTN.setOnClickListener {
            val section = binding.FaqTypeDropdownMenu.selectedItem.toString()
            val question = binding.questionET.text.toString().trim()
            val answer = binding.answerET.text.toString().trim()

            if (question.isEmpty() || answer.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val call = RetrofitClient.instance.addFaq(section, question, answer)
            call.enqueue(object : Callback<Map<String, Any>> {
                override fun onResponse(
                    call: Call<Map<String, Any>>,
                    response: Response<Map<String, Any>>
                ) {
                    if (response.isSuccessful && response.body()?.get("status") == 200.0) {
                        Toast.makeText(this@AddFaqActivity, "FAQ added!", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this@AddFaqActivity, "Addition failed", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                    Toast.makeText(this@AddFaqActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun setupSpinner() {
        val spinner: Spinner = binding.FaqTypeDropdownMenu
        ArrayAdapter.createFromResource(
            this,
            R.array.faq_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}
