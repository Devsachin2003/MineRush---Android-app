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

    private var onItemClickListener: OnItemClickListeners? = null

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.nameDisplayMTV)
        val roleTV: TextView = itemView.findViewById(R.id.userRoleDisplayMTV)
        val emailTV: TextView = itemView.findViewById(R.id.emailDisplayMTV)
        val phoneTV: TextView = itemView.findViewById(R.id.phoneDisplayMTV)
        val deleteUserBT: Button = itemView.findViewById(R.id.deleteUserBT)
    }

    interface OnItemClickListeners {
        fun onDelete(article: ManageData, position: Int)
    }

    fun setClickListeners(onItemClick: OnItemClickListeners) {
        onItemClickListener = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.manage_user_layout, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = articleList[position]
        holder.nameTV.text = currentItem.nameTV
        holder.roleTV.text = currentItem.roleTV
        holder.emailTV.text = currentItem.emailTV
        holder.phoneTV.text = currentItem.phoneTV

        holder.deleteUserBT.setOnClickListener {
            onItemClickListener?.onDelete(currentItem, position)
        }
    }

    override fun getItemCount() = articleList.size
}
