package com.example.minerush.DataClass
data class MessageData(
        val text: String,
        val isUser: Boolean // True if it's the user's message, false if it's the bot's response
    )
