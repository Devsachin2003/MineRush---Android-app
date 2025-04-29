package com.example.minerush.User

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minerush.DataClass.UserMyProfileData
import com.example.minerush.R
import com.example.minerush.databinding.ActivityEditUserProfileBinding

class EditUserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditUserProfileBinding

    // Sample data, assuming it's fetched from an API or passed via intent
    private val userProfile = UserMyProfileData(
        username = "John Doe",
        gender = "Male",
        email = "johndoe@example.com",
        phone = "+1234567890",
        role = "Developer",
        picture = "http://example.com/image.jpg",  // URL or local path to the image
        organization = "Tech Corp"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the initial data to the UI
        displayUserData(userProfile)

        // Handle the listeners
        setListeners()
    }

    private fun displayUserData(user: UserMyProfileData) {
        // Display user details in the respective views
        binding.UserNameDisplay.text = user.username
        binding.UserPhoneDisplay.text = user.phone
        binding.userGenderDisplay.text = user.gender
        binding.userOrganizationDisplay.text = user.organization
        binding.UserRoleDisplay.text = user.role

        // If you want to pre-fill editable fields like EditText or Spinner, set them here
        // Fix: Each EditText should have its own ID for clarity
        binding.userNameDisplayLinearLayout.findViewById<EditText>(R.id.editTextUserName).setText(user.username)
        binding.userPhoneDisplayLinearLayout.findViewById<EditText>(R.id.editTextPhone).setText(user.phone)
        binding.userOrganizationDisplayLinearLayout.findViewById<EditText>(R.id.editTextOrganization).setText(user.organization)

        // Example for Gender Spinner:
        val genderAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.gender_array, // Gender array resource (define it in strings.xml)
            android.R.layout.simple_spinner_item
        )
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderDropDown.adapter = genderAdapter
        val genderPosition = genderAdapter.getPosition(user.gender)
        binding.genderDropDown.setSelection(genderPosition)
    }

    private fun setListeners() {
        binding.backIV.setOnClickListener {
            finish()  // Go back to the previous screen
        }

        binding.saveDetailsButton.setOnClickListener {
            // When the save button is clicked, update the user profile with the new data
            val updatedUser = getUpdatedUserData()

            // Show the updated data (you can also send it back to the previous activity or save it in a database)
            Toast.makeText(this, "Updated User: ${updatedUser.username}", Toast.LENGTH_SHORT).show()

            // You can do any further actions like sending data to the server or updating your database
        }
    }

    private fun getUpdatedUserData(): UserMyProfileData {
        // Get updated data from the EditText and Spinner fields
        val updatedUsername = binding.userNameDisplayLinearLayout.findViewById<EditText>(R.id.editTextUserName).text.toString()
        val updatedPhone = binding.userPhoneDisplayLinearLayout.findViewById<EditText>(R.id.editTextPhone).text.toString()
        val updatedOrganization = binding.userOrganizationDisplayLinearLayout.findViewById<EditText>(R.id.editTextOrganization).text.toString()

        val updatedGender = binding.genderDropDown.selectedItem.toString()  // Get the selected gender from the spinner

        return UserMyProfileData(
            username = updatedUsername,
            gender = updatedGender,
            email = userProfile.email,  // Email is not editable here, assuming it's constant
            phone = updatedPhone,
            role = userProfile.role,  // Role is not editable here, assuming it's constant
            picture = userProfile.picture,  // Picture is not being edited here
            organization = updatedOrganization
        )
    }
}
