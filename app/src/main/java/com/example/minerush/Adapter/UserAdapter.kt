package com.android.minerush

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.ManageData
import com.example.minerush.R

class UserAdapter(private val articleList: ArrayList<ManageData>) : RecyclerView.Adapter<UserAdapter.ArticleViewHolder>() {

    private var onItemClickListner:OnItemClickListeners? = null

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameTV: TextView = itemView.findViewById(R.id.usernameTV)
        val roleTV: TextView = itemView.findViewById(R.id.roleTV)
        val emailTV: TextView = itemView.findViewById(R.id.emailTV)
        val passwordTV: TextView = itemView.findViewById(R.id.passwordTV)
        val viewUserBT: Button = itemView.findViewById(R.id.viewUserBT)
    }

    interface OnItemClickListeners {
        fun onClick(article:ManageData,position: Int)
    }

    fun setClickListeners(onItemClick:OnItemClickListeners) {
        onItemClickListner = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.manage_user_layout, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = articleList[position]
        holder.usernameTV.text = currentItem.usernameTV
        holder.roleTV.text = currentItem.roleTV
        holder.emailTV.text = currentItem.emailTV
        holder.passwordTV.text = currentItem.passwordTV
        holder.viewUserBT.setOnClickListener {
            onItemClickListner?.onClick(currentItem,position)
        }

    }

    override fun getItemCount() = articleList.size
}