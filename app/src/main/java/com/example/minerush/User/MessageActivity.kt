package com.example.minerush

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minerush.Adapter.ChatAdapter
import com.example.minerush.DataClass.History
import com.example.minerush.databinding.ActivityMessageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class MessageActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMessageBinding
//    private lateinit var chatAdapter: ChatAdapter
//    private val chatHistory = mutableListOf<History>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        binding = ActivityMessageBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        handleWindowInsets()
//        setupRecyclerView()
//        setupListeners()
//    }
//
//    private fun handleWindowInsets() {
//        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
//            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
//            val navigationInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//
//            val bottomMargin = if (imeInsets.bottom > 0) imeInsets.bottom + 12 else navigationInsets.bottom
//            val params = binding.msgbox.layoutParams as ViewGroup.MarginLayoutParams
//            params.bottomMargin = bottomMargin
//            binding.msgbox.layoutParams = params
//
//            WindowInsetsCompat.CONSUMED
//        }
//    }
//
//    private fun setupRecyclerView() {
//        chatAdapter = ChatAdapter(chatHistory)
//        binding.messageList.apply {
//            adapter = chatAdapter
//            layoutManager = LinearLayoutManager(this@MessageActivity)
//        }
//    }
//
//    private fun setupListeners() {
//        binding.back.setOnClickListener {
//            finish()
//        }
//
//        binding.send.setOnClickListener {
//            val message = binding.msgbox.text.toString().trim()
//            if (message.isNotBlank()) {
//                sendMessage(message)
//                binding.msgbox.text.clear()
//            }
//        }
//    }
//
//    private fun sendMessage(message: String) {
//        // Add the user's message to chat history
//        val userMessage = History(parts = message, role = "user")
//        chatHistory.add(userMessage)
//        chatAdapter.notifyItemInserted(chatHistory.size - 1)
//        binding.messageList.scrollToPosition(chatHistory.size - 1)
//
//        // Prepare the chat request
//        val chatRequest = ChatResponseData(message = message, history = chatHistory)
//
//        // Make API call
//        com.example.minerush.api.RetrofitClient.instance.botmessage(chatRequest).enqueue(object : Callback<ChatResponseData> {
//            override fun onResponse(call: Call<ChatResponseData>, response: Response<ChatResponseData>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { chatResponse ->
//                        val botMessage = History(parts = chatResponse.message, role = "model")
//                        chatHistory.add(botMessage)
//                        chatAdapter.notifyItemInserted(chatHistory.size - 1)
//                        binding.messageList.scrollToPosition(chatHistory.size - 1)
//                    }
//                } else {
//                    println("Response Error: ${response.errorBody()?.string()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ChatResponseData>, t: Throwable) {
//                println("Request Failed: ${t.message}")
//            }
//        })
//    }
//}
