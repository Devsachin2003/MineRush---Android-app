package com.example.minerush.User

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.databinding.ActivityInstructionsBinding

class InstructionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstructionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityInstructionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }
    private fun setListeners() {
        binding.backIV.setOnClickListener{
            finish()
        }

        binding.glossaryCV.setOnClickListener {
            val intent = Intent(this, GlossaryActivity::class.java)
            startActivity(intent)
        }
        binding.faqsLayout.setOnClickListener {
            val intent = Intent(this, FaqsActivity::class.java)
            startActivity(intent)
        }

        binding.disclaimerCV.setOnClickListener {
            val intent = Intent(this, DisclaimerActivity::class.java)
            startActivity(intent)
        }
    }

}
