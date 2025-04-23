package com.example.minerush.Adapter


// ChatAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.R

class ChatAdapter(private val messages: MutableList<Pair<String, Boolean>>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTV: TextView = itemView.findViewById(R.id.messageTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            if (viewType == 0) R.layout.item_user_message else R.layout.item_bot_message,
            parent,
            false
        )
        return ChatViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].second) 0 else 1 // true = user, false = bot
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.messageTV.text = messages[position].first
    }

    override fun getItemCount(): Int = messages.size

    fun addMessage(message: String, isUser: Boolean) {
        messages.add(Pair(message, isUser))
        notifyItemInserted(messages.size - 1)
    }
}
