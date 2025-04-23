package com.example.minerush

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.lawbot.DataClasses.ChatRequest
import com.android.lawbot.DataClasses.ChatResponse
import com.android.lawbot.DataClasses.HistoryItem
import com.example.minerush.Adapter.ChatAdapter
import com.example.minerush.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatPageActivity : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private val messageList = mutableListOf<Pair<String, Boolean>>()
    private val historyList = mutableListOf<HistoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_page)

        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        chatRecyclerView = findViewById(R.id.chatRecyclerView)

        chatAdapter = ChatAdapter(messageList)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = chatAdapter

        sendButton.setOnClickListener {
            val userMsg = messageInput.text.toString().trim()
            if (userMsg.isNotEmpty()) {
                chatAdapter.addMessage(userMsg, true)
                messageInput.setText("")



                val validHistory = historyList.filter {
                    !it.user.isNullOrBlank() && !it.bot.isNullOrBlank()
                }


                val request = ChatRequest(userMsg, validHistory)

                val chatService = RetrofitClient.instance
                chatService.sendMessage(request).enqueue(object : Callback<ChatResponse> {
                    override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                        if (response.isSuccessful) {
                            val botMsg = response.body()?.message ?: "..."
                            chatAdapter.addMessage(botMsg, false)

                            // Safely filter the received history
                            val updatedHistory = response.body()?.history?.filter {
                                !it.user.isNullOrBlank() && !it.bot.isNullOrBlank()
                            } ?: emptyList()

                            historyList.clear()
                            historyList.addAll(updatedHistory)

                            chatRecyclerView.scrollToPosition(chatAdapter.itemCount - 1)
                        } else {
                            val errorBody = response.errorBody()?.string()
                            Log.d("TAG", "onResponse: $errorBody")
                            Toast.makeText(this@ChatPageActivity, "Server Error", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                        Log.d("TAG", "onFailure: " + t.message)
                        Toast.makeText(this@ChatPageActivity, "Connection Failed", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        findViewById<ImageView>(R.id.backIV).setOnClickListener {
            finish()
        }
    }
}
