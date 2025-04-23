package com.example.minerush.Admin
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.UserAdapter
import com.example.minerush.DataClass.ManageData
import com.example.minerush.databinding.ActivityManageUsersBinding

class ManageUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageUsersBinding
    private var articles = ArrayList<ManageData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityManageUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        redirects()
        setData()
        setAdapter()
    }

    private fun redirects() {
        binding.backIV.setOnClickListener {
            finish()
        }
    }

    private fun setData() {
        articles.add(ManageData("username", "role", "email", "password"))
        articles.add(ManageData("username", "role", "email", "password"))
        articles.add(ManageData("username", "role", "email", "password"))
        articles.add(ManageData("username", "role", "email", "password"))
        articles.add(ManageData("username", "role", "email", "password"))
    }

    private fun setAdapter() {
        binding.manageUsersRV.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter(articles)
        binding.manageUsersRV.adapter = adapter
        adapter.setClickListeners(object : UserAdapter.OnItemClickListeners{
            override fun onClick(article: ManageData, position: Int) {
                val intent = Intent(this@ManageUsersActivity,UserDetailsViewActivity::class.java)
                startActivity(intent)
            }
        })
    }

}
