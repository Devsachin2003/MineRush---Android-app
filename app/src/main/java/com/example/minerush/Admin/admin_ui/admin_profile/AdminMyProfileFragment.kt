package com.example.minerush.Admin.admin_ui.admin_profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.minerush.DataClass.AdminProfileResponse
import com.example.minerush.R
import com.example.minerush.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminMyProfileFragment : Fragment() {

    private lateinit var username: TextView
    private lateinit var gender: TextView
    private lateinit var emailView: TextView
    private lateinit var phone: TextView
    private lateinit var responsibility: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Replace with actual email (use SharedPreferences or arguments if needed)
        val email = "dev@gmail.com"

        // Initialize views
        username = view.findViewById(R.id.usernameMyProfileDisplay)
        gender = view.findViewById(R.id.genderDisplay)
        emailView = view.findViewById(R.id.emailDisplay)
        phone = view.findViewById(R.id.phoneDisplay)
        responsibility = view.findViewById(R.id.respDisplay)

        // Set click listener for Edit Profile button
        val editButton = view.findViewById<View>(R.id.editAdminProfileBT)
        editButton.setOnClickListener {
            val intent = Intent(requireContext(), EditAdminProfileActivity::class.java)
            startActivity(intent)
        }

        // Fetch profile data
        loadAdminProfile(email)
    }

    private fun loadAdminProfile(email: String) {
        RetrofitClient.instance.getAdminProfile(email)
            .enqueue(object : Callback<AdminProfileResponse> {
                override fun onResponse(
                    call: Call<AdminProfileResponse>,
                    response: Response<AdminProfileResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val admin = response.body()!!.admin
                        username.text = admin.name
                        gender.text = admin.gender
                        emailView.text = admin.email
                        phone.text = admin.phone
                        responsibility.text = admin.responsibility
                    } else {
                        Toast.makeText(requireContext(), "Profile not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AdminProfileResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Failed to load profile", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
