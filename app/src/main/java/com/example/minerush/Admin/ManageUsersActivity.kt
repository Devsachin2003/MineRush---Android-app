package com.example.minerush.Admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.minerush.UserAdapter
import com.example.minerush.DataClass.ManageData
import com.example.minerush.DataClass.ManageUsersResponse
import com.example.minerush.api.ApiService
import com.example.minerush.api.RetrofitClient
import com.example.minerush.api.serverresponse.CommonResponse
import com.example.minerush.databinding.ActivityManageUsersBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageUsersBinding
    private var userList = ArrayList<ManageData>()
    private lateinit var adapter: UserAdapter
    private val apiService = RetrofitClient.getClient().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchUsers()

        binding.backIV.setOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter(userList)
        binding.manageUsersRV.layoutManager = LinearLayoutManager(this)
        binding.manageUsersRV.adapter = adapter

        adapter.setClickListeners(object : UserAdapter.OnItemClickListeners {
            override fun onDelete(article: ManageData, position: Int) {
                apiService.deleteUser(article.emailTV).enqueue(object : Callback<CommonResponse> {
                    override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
                        if (response.isSuccessful) {
                            userList.removeAt(position)
                            adapter.notifyItemRemoved(position)
                            Toast.makeText(this@ManageUsersActivity, "User deleted successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@ManageUsersActivity, "Failed to delete user", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                        Toast.makeText(this@ManageUsersActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }

    private fun fetchUsers() {
        apiService.getUsers().enqueue(object : Callback<ManageUsersResponse> {
            override fun onResponse(call: Call<ManageUsersResponse>, response: Response<ManageUsersResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    userList.clear()
                    userList.addAll(response.body()!!.data)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@ManageUsersActivity, "No users found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ManageUsersResponse>, t: Throwable) {
                Toast.makeText(this@ManageUsersActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
