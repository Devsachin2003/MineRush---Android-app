package com.example.minerush.User.user_ui.user_home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.minerush.ChatPageActivity
import com.example.minerush.DataClass.StatusResponse
import com.example.minerush.User.ExploreInternshipsActivity
import com.example.minerush.User.InstructionsActivity
import com.example.minerush.User.UserSideRulesPageActivity
import com.example.minerush.api.RetrofitClient
import com.example.minerush.databinding.FragmentUserHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.minerush.api.serverresponse.CommonResponse

class UserHomeFragment : Fragment() {

    private var _binding: FragmentUserHomeBinding? = null
    private val binding get() = _binding!!

    private var email:String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserHomeBinding.inflate(inflater, container, false)
        setListeners()

        binding.newChatCL.setOnClickListener {
            startActivity(Intent(requireContext(), ChatPageActivity::class.java))
        }

        binding.rulesAndActsLayout.setOnClickListener {
            startActivity(Intent(requireContext(), UserSideRulesPageActivity::class.java))
        }

        binding.instructionsLayout.setOnClickListener {
            startActivity(Intent(requireContext(), InstructionsActivity::class.java))
        }

        binding.internshipFinderLayout.setOnClickListener {
            startActivity(Intent(requireContext(), ExploreInternshipsActivity::class.java))
        }

        val sharedPref = requireActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        email = sharedPref.getString("email", "").toString()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        // Call fetchStatusMessage here to load status on fragment view creation
        fetchStatusMessage()
    }

    private fun fetchStatusMessage() {
        val api = RetrofitClient.instance
        val call = api.getUserHomePageStatus(email = email)

        call.enqueue(object : Callback<StatusResponse> {
            override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!
                    binding.applicationStatusTV.visibility = View.VISIBLE
                    binding.applicationStatusTV.text =
                        "Hello ${data.name}, your internship application status is: ${data.status}"
                    Log.d("UserHomeFragment", response.body().toString())
                } else {
                    Log.d("UserHomeFragment", response.errorBody()!!.string())
                    binding.applicationStatusTV.visibility = View.VISIBLE
                    binding.applicationStatusTV.text = "Failed to load status."
                }
            }

            override fun onFailure(call: Call<StatusResponse>, t: Throwable) {

                binding.applicationStatusTV.visibility = View.VISIBLE
                binding.applicationStatusTV.text = "Error: ${t.message}"
            }
        })
    }

}
