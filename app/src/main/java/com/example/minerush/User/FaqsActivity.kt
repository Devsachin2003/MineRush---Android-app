package com.example.minerush.User

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.FaqsAboutTheChatAdaptor
import com.android.minerush.FaqsStakeholdersAndCustomersAdaptor
import com.android.minerush.FaqsUsageChatBotAdaptor
import com.example.minerush.DataClass.FaqsData
import com.example.minerush.databinding.ActivityFaqsBinding

class FaqsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaqsBinding
    private var basics = ArrayList<FaqsData>()
    private var stakeholders = ArrayList<FaqsData>() // Second list for another adapter
    private var usageChatbot = ArrayList<FaqsData>() // Third list for another adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFaqsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        redirects()
        setData()
        setAdapters() // Initialize adapters here
    }

    private fun setListeners() {
        binding.backIV.setOnClickListener{
            finish()
        }
    }

    private fun redirects() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }

    private fun setData() {
        basics.add(FaqsData("What is a chatbot", "A Chatbot is a computer program that uses Artificial Intelligence (AI) and Natural Language Processing (NLP) to understand customer questions and automate responses to them, imitating human conversation."))
        basics.add(FaqsData("What kind of information can the Chatbot provide?", "The Chatbot can provide information and answer queries related to various Acts, Rules, and Regulations applicable to the mining industry, as well as DGMS Circulars, CoI Proceedings, and land-related laws."))
        basics.add(FaqsData("Who can use the chatbot?", "The Chatbot is available for stakeholders and customers in the mining industry who need information or have queries regarding mining-related regulations and laws."))
        stakeholders.add(FaqsData("Who are the stakeholders?", "Stakeholders refer to individuals or organizations with a vested interest in the project."))
        stakeholders.add(FaqsData("How can stakeholders and customers benefit from this portal?", "For Stakeholders: The portal provides quick access to updated coal mining rules and regulations, ensuring compliance and reducing legal risks. It supports informed decision-making and strategic planning based on industry standards.  \n" +
                "\n" +
                "For Customers: The portal offers transparency into mining practices and ensures trust by highlighting adherence to safety and environmental regulations."))
        stakeholders.add(FaqsData("Can stakeholders and customers provide feedback or suggestion for improving the portal's services?", " Yes, stakeholders and customers can share feedback or suggestions through the contact us section on the portal, helping us enhance its features and user experience."))
        usageChatbot.add(FaqsData("How do I apply for an internship through the portal?", "Log in to the portal and navigate to the \"Explore Internship\" section, where a list of available internships will be displayed. Select a company of interest and click the \"Apply\" button. This will open an application form where you can fill in and submit your details."))
        usageChatbot.add(FaqsData(" Is there an option to filter internships by location or whether they are online or offline?", "Yes, the portal provides filters to sort internships by location and mode (online or offline) for your convenience."))
        usageChatbot.add(FaqsData("How will I know if my internship application has been accepted? ", "Once the admin approves your application, you will receive a notification confirming that it has been forwarded to the company."))
    }

    private fun setAdapters() {
        // Setting up the first adapter for aboutTheChatbotRV
        binding.aboutTheChatbotRV.layoutManager = LinearLayoutManager(this)
        val aboutTheChatbotAdapter = FaqsAboutTheChatAdaptor(basics)
        binding.aboutTheChatbotRV.adapter = aboutTheChatbotAdapter

        // Setting up the second adapter for stakeholdersAndCustomersRV
        binding.stakeholdersAndCustomersRV.layoutManager = LinearLayoutManager(this)
        val stakeholdersAdapter = FaqsStakeholdersAndCustomersAdaptor(stakeholders)
        binding.stakeholdersAndCustomersRV.adapter = stakeholdersAdapter

        // Setting up the third adapter for usageChatbotRV
        binding.usageChatbotRV.layoutManager = LinearLayoutManager(this)
        val usageChatbotAdapter = FaqsUsageChatBotAdaptor(usageChatbot)
        binding.usageChatbotRV.adapter = usageChatbotAdapter
    }
}
