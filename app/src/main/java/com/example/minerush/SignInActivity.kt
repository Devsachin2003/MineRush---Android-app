package com.example.minerush

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.api.RetrofitClient
import com.example.minerush.api.serverresponse.SignUpResponse
import com.example.minerush.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setUpListeners()
        setDropDownAdapter()
        }

    private fun setDropDownAdapter() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            com.example.minerush.R.array.dropdown_items, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.roleSpinner.adapter = adapter
    }

    private fun setUpListeners() {
        binding.signBTN.setOnClickListener {
            signup()
        }

        binding.signBackIV.setOnClickListener {
            finish()
        }
    }

    private fun signup() {
        val name = binding.signNameET.text.toString()
        val email = binding.emailET.text.toString()
        val gender = binding.signGenderET.text.toString()
        val phone = binding.signMobileET.text.toString()
        val organization = binding.signOrganizationET.text.toString()
        val username = binding.userNameET.text.toString()
        val role = binding.roleSpinner.selectedItem.toString()
        val confirmpassword = binding.signRepeatPasswordET.text.toString()
        val password = binding.signPasswordET.text.toString()

        // Empty fields check
        if (name.isEmpty()) {
            binding.signNameET.error = "Name is required"
            return
        }
        if (email.isEmpty()) {
            binding.emailET.error = "Email is required"
            return
        }
        if (phone.isEmpty()) {
            binding.signMobileET.error = "Phone number is required"
            return
        }
        if (password.isEmpty()) {
            binding.signPasswordET.error = "Password is required"
            return
        }

        // Email validation
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailET.error = "Invalid email address"
            return
        }

        // Phone number validation (10-digit numeric)
        if (!phone.matches(Regex("^[0-9]{10}$"))) {
            binding.signMobileET.error = "Invalid phone number"
            return
        }

        // Password mismatch validation
        if (password != confirmpassword) {
            binding.signRepeatPasswordET.error = "Passwords do not match"
            return
        }

        // Proceed with API call if all validations pass
        com.example.minerush.api.RetrofitClient.instance.register(name, email, gender, phone, username, organization, password, confirmpassword, role).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    val signUpResponse = response.body()
                    Log.i("responseS", signUpResponse?.message.toString())

                    if (signUpResponse != null) {
                        Toast.makeText(this@SignInActivity, "Registration successful!", Toast.LENGTH_LONG).show()
                        finish() // Close the sign-up page
                    } else {
                        Toast.makeText(this@SignInActivity, signUpResponse?.message ?: "Registration failed", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@SignInActivity, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Toast.makeText(this@SignInActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }



}