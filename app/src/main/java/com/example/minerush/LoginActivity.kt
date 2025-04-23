package com.example.minerush

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.Admin.AdminSideNavActivity
import com.example.minerush.User.UserSideNavActivity
import com.example.minerush.api.serverresponse.LoginResponse
import com.example.minerush.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.signInBTN.setOnClickListener {
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        binding.loginBTN.setOnClickListener {
//            val intent = Intent(this,AdminHomeFragment::class.java)
//            startActivity(intent)
            login()
        }
    }

    private fun login() {
        val email = binding.usernameET.text.toString()
        val password = binding.passwordET.text.toString()

        com.example.minerush.api.RetrofitClient.instance.login(email, password).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Log.i("responseS", loginResponse?.message.toString())

                    if (loginResponse != null) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login successful! Welcome, ${loginResponse.role.capitalize()}!",
                            Toast.LENGTH_LONG
                        ).show()

                        // Redirect based on role
                        val intent = when (loginResponse.role.lowercase()) {
                            "admin" -> Intent(this@LoginActivity, AdminSideNavActivity::class.java)
                            "user" -> Intent(this@LoginActivity, UserSideNavActivity::class.java)
                            else -> {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Unknown role: ${loginResponse.role}",
                                    Toast.LENGTH_LONG
                                ).show()
                                return
                            }
                        }
                        startActivity(intent)
                    }
                } else {
                    Log.i("responseF", response.message())
                    Toast.makeText(
                        this@LoginActivity,
                        "Login failed: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }


}