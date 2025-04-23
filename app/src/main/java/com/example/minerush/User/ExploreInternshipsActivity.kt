package com.example.minerush.User

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.ExploreInternshipAdaptor
import com.example.minerush.DataClass.ExploreInternData
import com.example.minerush.R
import com.example.minerush.databinding.ActivityExploreInternshipsBinding

class ExploreInternshipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExploreInternshipsBinding
    private var internships = ArrayList<ExploreInternData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExploreInternshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the SearchView
//        setupSearchView()
        redirects()
        setData()
        setAdapter()
        // Set up the Spinner
        setupSpinner()
    }

    /*private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle the search query submission
                if (!query.isNullOrEmpty()) {
                    // Implement your search logic here, e.g., filtering a list
                    // Example: filterInternshipList(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle text changes in the search bar for live filtering
                if (!newText.isNullOrEmpty()) {
                    // Implement your live filtering logic here
                    // Example: filterInternshipList(newText)
                } else {
                    // Reset the filter if the search query is empty
                    // Example: resetInternshipList()
                }
                return true
            }
        })
    }*/

    private fun setData() {
        internships.add(ExploreInternData("internshipRole", "companyName","location", "mode","description"))
        internships.add(ExploreInternData("internshipRole1", "companyName","location", "mode","description"))
        internships.add(ExploreInternData("internshipRole2", "companyName","location", "mode","description"))
        internships.add(ExploreInternData("internshipRole3", "companyName","location", "mode","description"))
        internships.add(ExploreInternData("internshipRole4", "companyName","location", "mode","description"))

    }

    private fun setupSpinner() {
        val spinner: Spinner = binding.InternshipTypeDropdownMenu
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.internship_type,  // Ensure this array is defined in strings.xml
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        // Set the listener for item selection
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Get the selected item
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Handle the selection (e.g., update UI or perform an action)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case where no item is selected, if needed
            }
        }

        val spinner2: Spinner = binding.locationDropdownMenu // Ensure you have a second Spinner in your layout
        ArrayAdapter.createFromResource(
            this,
            R.array.locations,  // Define this array in strings.xml for the second spinner
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner2.adapter = adapter
        }

        // Set the listener for the second spinner
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Handle selection of second spinner
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case where nothing is selected in the second spinner
            }
        }
    }


    private fun redirects() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }

    private fun setAdapter() {
            binding.InternshipRV.layoutManager = LinearLayoutManager(this)
            val adapter = ExploreInternshipAdaptor(internships)
            binding.InternshipRV.adapter = adapter
            adapter.setClickListeners(object : ExploreInternshipAdaptor.OnItemClickListeners {
                override fun onClick(article: ExploreInternData, position: Int) {
                    val intent = Intent(
                        this@ExploreInternshipsActivity,
                        ApplicationFormActivity::class.java
                    )
                    startActivity(intent)
                }
            })
        }
}
