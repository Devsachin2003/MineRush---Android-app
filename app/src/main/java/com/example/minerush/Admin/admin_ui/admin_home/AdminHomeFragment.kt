package com.example.minerush.Admin.admin_ui.admin_home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.minerush.Admin.AddFaqActivity
import com.example.minerush.Admin.AddGlossaryActivity
import com.example.minerush.Admin.AddInternshipDetailsActivity
import com.example.minerush.Admin.ManageUsersActivity
import com.example.minerush.Admin.NewInternApplicantActivity
import com.example.minerush.Admin.RecentlyRegisteredUsersViewDetailsActivity
import com.example.minerush.databinding.FragmentAdminHomeBinding

class AdminHomeFragment : Fragment() {

    private var _binding: FragmentAdminHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdminHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.internshipCL.setOnClickListener {
            val intent = Intent(requireContext(), AddInternshipDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.glossaryCL.setOnClickListener {
            val intent = Intent(requireContext(), AddGlossaryActivity::class.java)
            startActivity(intent)
        }
        binding.faqsCL.setOnClickListener {
            val intent = Intent(requireContext(), AddFaqActivity::class.java)
            startActivity(intent)
        }
        binding.manageusersCL.setOnClickListener {
            val intent = Intent(requireContext(), ManageUsersActivity::class.java)
            startActivity(intent)
        }
        binding.recentlyRegisteredUsersCL.setOnClickListener {
            val intent = Intent(requireContext(), RecentlyRegisteredUsersViewDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.viewRecentApplicationsCL.setOnClickListener {
            val intent = Intent(requireContext(), NewInternApplicantActivity::class.java)
            startActivity(intent)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}