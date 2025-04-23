package com.example.minerush.Admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.databinding.ActivityUpdateExistingRuleBinding

class UpdateExistingRuleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateExistingRuleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateExistingRuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()
        setRulePageSelectionAdapter()

    }

    private fun setRulePageSelectionAdapter() {

    }

    private fun setUpListeners() {
        binding.backIV.setOnClickListener {
        finish()
        }
    }

}