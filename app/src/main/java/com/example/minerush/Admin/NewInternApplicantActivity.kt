package com.example.minerush.Admin
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.InternApprovalAdapter
import com.example.minerush.DataClass.NewInternApproveData
import com.example.minerush.databinding.ActivityNewInternApplicantBinding

class NewInternApplicantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewInternApplicantBinding
    private var applications = ArrayList<NewInternApproveData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNewInternApplicantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        redirects()
        setData()
        setAdapter()
    }

    private fun setData() {
        applications.add(NewInternApproveData("name", "email", "phone", "area_of_interest"))
        applications.add(NewInternApproveData("name", "email", "phone", "area_of_interest"))
        applications.add(NewInternApproveData("name", "email", "phone", "area_of_interest"))
        applications.add(NewInternApproveData("name", "email", "phone", "area_of_interest"))
        applications.add(NewInternApproveData("name", "email", "phone", "area_of_interest"))
    }

    private fun redirects() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }

    private fun setAdapter() {
        binding.recentApplicationsRV.layoutManager = LinearLayoutManager(this)
        val adapter = InternApprovalAdapter(applications)
        binding.recentApplicationsRV.adapter = adapter
        adapter.setClickListeners(object : InternApprovalAdapter.OnItemClickListeners {
            override fun onClick(article: NewInternApproveData, position: Int) {
                val intent = Intent(
                    this@NewInternApplicantActivity,
                    ApplicantDetailsForRecentInternApplicationsActivity::class.java
                )
                startActivity(intent)
            }
        })
    }
}