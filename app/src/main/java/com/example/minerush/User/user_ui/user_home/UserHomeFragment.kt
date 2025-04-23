package com.example.minerush.User.user_ui.user_home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.minerush.ChatPageActivity
import com.example.minerush.User.ExploreInternshipsActivity
import com.example.minerush.User.InstructionsActivity
import com.example.minerush.User.UserSideRulesPageActivity
import com.example.minerush.databinding.FragmentUserHomeBinding

class UserHomeFragment : Fragment() {

    private var _binding: FragmentUserHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserHomeBinding.inflate(inflater, container, false)
        setListeners()
        binding.newChatCL.setOnClickListener {
            val intent = Intent(requireContext(), ChatPageActivity::class.java)
            startActivity(intent)
        }

        binding.rulesAndActsLayout.setOnClickListener {
            val intent = Intent(requireContext(), UserSideRulesPageActivity::class.java)
            startActivity(intent)
        }

        binding.instructionsLayout.setOnClickListener {
            val intent = Intent(requireContext(), InstructionsActivity::class.java)
            startActivity(intent)
        }

        binding.internshipFinderLayout.setOnClickListener {
            val intent = Intent(requireContext(), ExploreInternshipsActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {

    }
}
