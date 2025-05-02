package com.example.minerush.Admin.admin_ui.admin_profile

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R

class EditAdminProfileActivity : AppCompatActivity() {

    private lateinit var backIV: ImageView
    private lateinit var editTextUserName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var editTextOrganization: EditText
    private lateinit var uploadButton: Button
    private lateinit var resetButton: Button
    private lateinit var saveDetailsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_admin_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        backIV = findViewById(R.id.backIV)
        editTextUserName = findViewById(R.id.editTextUserName)
        editTextPhone = findViewById(R.id.editTextPhone)
        genderSpinner = findViewById(R.id.genderDropDown)
        uploadButton = findViewById(R.id.uploadButton)
        resetButton = findViewById(R.id.resetButton)
        saveDetailsButton = findViewById(R.id.saveDetailsButton)

        // Set back button click
        backIV.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Populate spinners
        val genderOptions = arrayOf("Select Gender", "Male", "Female", "Other")

        genderSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genderOptions)

        // Upload button action
        uploadButton.setOnClickListener {
            Toast.makeText(this, "Upload image logic goes here", Toast.LENGTH_SHORT).show()
        }

        // Reset button action
        resetButton.setOnClickListener {
            editTextUserName.text.clear()
            editTextPhone.text.clear()
            editTextOrganization.text.clear()
            genderSpinner.setSelection(0)
        }

        // Save details button action
        saveDetailsButton.setOnClickListener {
            val name = editTextUserName.text.toString()
            val phone = editTextPhone.text.toString()
            val gender = genderSpinner.selectedItem.toString()

            // Implement your validation and save logic here
            Toast.makeText(this, "Saved: $name, $phone, $gender", Toast.LENGTH_SHORT).show()
        }
    }
}
