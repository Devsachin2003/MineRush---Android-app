package com.example.minerush.User.user_ui.user_profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView  // Change TextView to ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.minerush.DataClass.UserMyProfileData
import com.example.minerush.R
import com.example.minerush.User.EditUserProfileActivity
import com.example.minerush.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserMyProfileFragment : Fragment() {

    private lateinit var usernameText: TextView
    private lateinit var genderText: TextView
    private lateinit var emailText: TextView
    private lateinit var phoneText: TextView
    private lateinit var roleText: TextView
    private lateinit var pictureIV: ImageView  // Corrected: Changed to ImageView
    private lateinit var organizationText: TextView
    private lateinit var editProfileCLText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_my_profile, container, false)

        usernameText = view.findViewById(R.id.usernameMyProfileDisplay)
        genderText = view.findViewById(R.id.genderMyProfileDisplay)
        emailText = view.findViewById(R.id.emailDisplay)
        phoneText = view.findViewById(R.id.phoneDisplay)
        roleText = view.findViewById(R.id.roleDisplay)
        pictureIV = view.findViewById(R.id.pictureIV)  // Corrected: Bind the ImageView
        organizationText = view.findViewById(R.id.organizationDisplay)
        editProfileCLText = view.findViewById(R.id.editProfileCL)  // Add this to bind the LinearLayout

        // Set click listener for edit profile
        editProfileCLText.setOnClickListener {
            // Start EditUserProfileActivity when clicked
            val intent = Intent(requireContext(), EditUserProfileActivity::class.java)
            startActivity(intent)
        }

        fetchUserProfile()

        return view
    }

    private fun fetchUserProfile() {
        val sharedPref = requireActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val email = sharedPref.getString("email", null)

        if (email.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Email not found. Please log in again.", Toast.LENGTH_SHORT).show()
            Log.e("UserProfile", "SharedPreferences: Email is null or empty")
            return
        }

        Log.d("UserProfile", "Fetching profile for email: $email")

        // Retrofit sends email as a query parameter (not session-based)
        val call = RetrofitClient.instance.getUserProfile(email)
        call.enqueue(object : Callback<UserMyProfileData> {
            override fun onResponse(
                call: Call<UserMyProfileData>,
                response: Response<UserMyProfileData>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val user = response.body()!!

                    usernameText.text = user.username
                    genderText.text = user.gender
                    emailText.text = user.email
                    phoneText.text = user.phone
                    roleText.text = user.role
                    organizationText.text = user.organization

                    // Load the profile picture into the ImageView using Glide
                    Glide.with(requireContext())
                        .load(user.picture)  // Assuming 'user.picture' is a URL or file path
                        .error(R.drawable.indian_explosive_act)  // Placeholder if image fails to load
                        .into(pictureIV)

                    Log.d("UserProfile", "Profile fetched successfully")
                } else {
                    Toast.makeText(requireContext(), "User not found or server error", Toast.LENGTH_SHORT).show()
                    Log.e("UserProfile", "Unsuccessful response: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserMyProfileData>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                Log.e("UserProfile", "Network failure: ${t.localizedMessage}")
            }
        })
    }
}
