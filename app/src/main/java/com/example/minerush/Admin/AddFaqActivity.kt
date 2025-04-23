package com.example.minerush.Admin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R
import com.example.minerush.databinding.ActivityAddFaqBinding
import com.example.minerush.databinding.ActivityAddInternshipDetailsBinding

class AddFaqActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFaqBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        setupSpinner()
//        setFAQSelectionAdapter()
    }

//    private fun setFAQSelectionAdapter() {
//        val faq = resources.getStringArray(R.array.FAQ_sections)
//        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu, faq)
//        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
//        autocompleteTV.setAdapter(arrayAdapter)
//    }

    private fun setListeners() {
        binding.backIV.setOnClickListener{
            finish()
        }
    }

    private fun setupSpinner() {
        val spinner: Spinner = binding.FaqTypeDropdownMenu
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.faq_types,  // Ensure this array is defined in strings.xml
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }
}
