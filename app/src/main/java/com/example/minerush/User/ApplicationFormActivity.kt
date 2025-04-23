package com.example.minerush.User

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.databinding.ActivityApplicationFormBinding

class ApplicationFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApplicationFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityApplicationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    private fun setListeners(){
    binding.backCL.setOnClickListener {
        val intent = Intent(this, ExploreInternshipsActivity::class.java)
        startActivity(intent)
    }
    }
}