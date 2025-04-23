package com.example.minerush.Admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.RecentUserAdapter
import com.example.minerush.DataClass.RecentData
import com.example.minerush.databinding.ActivityRecentlyRegisteredUsersViewDetailsBinding

class RecentlyRegisteredUsersViewDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentlyRegisteredUsersViewDetailsBinding
    private var recentusers = ArrayList<RecentData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecentlyRegisteredUsersViewDetailsBinding.inflate(layoutInflater)
        setData()
        setAdapter()
        redirects()
        setContentView(binding.root)
    }

    private fun redirects() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }

    private fun setData() {
        recentusers.add(RecentData("name", "email", "phone", "role"))
        recentusers.add(RecentData("name", "email", "phone", "role"))
        recentusers.add(RecentData("name", "email", "phone", "role"))
        recentusers.add(RecentData("name", "email", "phone", "role"))
        recentusers.add(RecentData("name", "email", "phone", "role"))

    }

    private fun setAdapter() {
        binding.recentUsersRV.layoutManager = LinearLayoutManager(this)
        val adapter = RecentUserAdapter(recentusers)
        binding.recentUsersRV.adapter = adapter
        adapter.setClickListeners(object : RecentUserAdapter.OnItemClickListeners {
            override fun onClick(article: RecentData, position: Int) {
                val intent = Intent(
                    this@RecentlyRegisteredUsersViewDetailsActivity,
                    UserDetailsForRecentlyRegisteredUserActivity::class.java
                )
                startActivity(intent)
            }
        })
    }

}
