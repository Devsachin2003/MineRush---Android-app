package com.example.minerush.Admin.admin_ui.admin_profile

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minerush.R

class EditAdminProfileActivity : AppCompatActivity() {

    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private lateinit var backIV: ImageView
    private lateinit var editTextUserName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var genderSpinner: Spinner
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

        // Populate gender spinner
        val genderOptions = arrayOf("Select Gender", "Male", "Female", "Other")
        genderSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genderOptions)

        // Register image picker result launcher
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val selectedImageUri = result.data?.data
                if (selectedImageUri != null) {
                    Toast.makeText(this, "Selected: $selectedImageUri", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Upload button logic
        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            pickImageLauncher.launch(Intent.createChooser(intent, "Select Picture"))
        }

        // Reset button logic
        resetButton.setOnClickListener {
            editTextUserName.text.clear()
            editTextPhone.text.clear()
            genderSpinner.setSelection(0)
        }

        // Save button logic
        saveDetailsButton.setOnClickListener {
            val name = editTextUserName.text.toString()
            val phone = editTextPhone.text.toString()
            val gender = genderSpinner.selectedItem.toString()
            Toast.makeText(this, "Saved: $name, $phone, $gender", Toast.LENGTH_SHORT).show()
        }
    }
}
