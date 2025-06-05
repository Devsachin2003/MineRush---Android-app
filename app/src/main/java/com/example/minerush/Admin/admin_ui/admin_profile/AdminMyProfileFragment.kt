package com.example.minerush.Admin.admin_ui.admin_profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.minerush.DataClass.AdminProfileResponse
import com.example.minerush.R
import com.example.minerush.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminMyProfileFragment : Fragment() {

    private lateinit var name: TextView
    private lateinit var gender: TextView
    private lateinit var emailView: TextView
    private lateinit var phone: TextView
    private lateinit var responsibility: TextView
    private lateinit var profileimage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = "dev@gmail.com" // replace with dynamic email if needed

        // Initialize views
        name = view.findViewById(R.id.AdminNameDisplay)
        gender = view.findViewById(R.id.adminGenderDisplay)
        emailView = view.findViewById(R.id.AdminEmailDisplay)
        phone = view.findViewById(R.id.AdminPhoneDisplay)
        responsibility = view.findViewById(R.id.respDisplay)
        profileimage = view.findViewById(R.id.AdminProfileImageIV)

        val editButton = view.findViewById<View>(R.id.editAdminProfileBT)
        editButton.setOnClickListener {
            val intent = Intent(requireContext(), EditAdminProfileActivity::class.java)
            startActivity(intent)
        }

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
                        name.text = admin.name
                        gender.text = admin.gender
                        emailView.text = admin.email
                        phone.text = admin.phone
                        responsibility.text = admin.responsibility

                        // ✅ Load profile image with Glide
                        if (!admin.profile_image.isNullOrEmpty()) {
                            Glide.with(requireContext())
                                .load(admin.profile_image)
                                .placeholder(R.drawable.my_profile) // fallback image
                                .error(R.drawable.error_image)       // in case of error
                                .into(profileimage)
                        } else {
                            profileimage.setImageResource(R.drawable.my_profile)
                        }
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
