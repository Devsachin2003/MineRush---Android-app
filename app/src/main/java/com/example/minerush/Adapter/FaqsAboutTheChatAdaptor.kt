package com.android.minerush

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.FaqsData
import com.example.minerush.R

class FaqsAboutTheChatAdaptor(private val userList: ArrayList<FaqsData>) : RecyclerView.Adapter<FaqsAboutTheChatAdaptor.UsersViewHolder>() {

    private var onItemClickListener: OnItemClickListeners? = null

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val faqQuestionTV: TextView = itemView.findViewById(R.id.faqTermTV)
        val answerTV: TextView = itemView.findViewById(R.id.answerDisplayTV)
        val up: ImageView = itemView.findViewById(R.id.uparrow)
        val down: ImageView = itemView.findViewById(R.id.downarrow)
        val expandLay: LinearLayout = itemView.findViewById(R.id.expandLay)
    }

    interface OnItemClickListeners {
        fun onClick(article: FaqsData, position: Int)
    }

    fun setClickListeners(onItemClick: OnItemClickListeners) {
        onItemClickListener = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.faqs_items, parent, false)
        return UsersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = userList[position]

        // Set text for each field
        holder.faqQuestionTV.text = currentItem.questionTV
        holder.answerTV.text = currentItem.answerTV

        // Set visibility based on isExpanded state
        holder.expandLay.visibility = if (currentItem.isExpanded) View.VISIBLE else View.GONE
        holder.down.visibility = if (currentItem.isExpanded) View.GONE else View.VISIBLE
        holder.up.visibility = if (currentItem.isExpanded) View.VISIBLE else View.GONE

        // Expand action
        holder.down.setOnClickListener {
            currentItem.isExpanded = true
            notifyItemChanged(position)  // Notify adapter to refresh item view
        }

        // Collapse action
        holder.up.setOnClickListener {
            currentItem.isExpanded = false
            notifyItemChanged(position)  // Notify adapter to refresh item view
        }

        // Optional: Handle item click
        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(currentItem, position)
        }
    }

    override fun getItemCount() = userList.size
}
