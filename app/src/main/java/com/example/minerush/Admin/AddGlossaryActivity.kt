package com.example.minerush.Admin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.ActivityAddGlossaryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGlossaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddGlossaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddGlossaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.addTermBTN.setOnClickListener {
            val term = binding.termET.text.toString().trim()
            val definition = binding.glossaryDefinitionTV.text.toString().trim()

            if (term.isEmpty() || definition.isEmpty()) {
                Toast.makeText(this, "Both term and definition are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            addGlossary(term, definition)
        }
    }

    private fun addGlossary(term: String, definition: String) {
        // Retrieve the Authorization token (e.g., from SharedPreferences or a global session)
        val token = "Bearer your_token_here" // Replace this with actual token retrieval logic

        val call = RetrofitClient.instance.addGlossary(token, term, definition)

        call.enqueue(object : Callback<Map<String, Any>> {
            override fun onResponse(call: Call<Map<String, Any>>, response: Response<Map<String, Any>>) {
                if (response.isSuccessful && response.body()?.get("status") == 200.0) {
                    Toast.makeText(this@AddGlossaryActivity, "Term added successfully!", Toast.LENGTH_SHORT).show()
                    finish() // Go back to the previous screen after successful submission
                } else {
                    val errorMessage = response.body()?.get("message") ?: "Error occurred"
                    Toast.makeText(this@AddGlossaryActivity, errorMessage.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                Toast.makeText(this@AddGlossaryActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
