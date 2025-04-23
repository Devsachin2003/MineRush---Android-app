package com.android.minerush

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.RecentData
import com.example.minerush.R

class RecentUserAdapter(private val userList: ArrayList<RecentData>) : RecyclerView.Adapter<RecentUserAdapter.UsersViewHolder>() {

    private var onItemClickListner:OnItemClickListeners? = null

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val roleTV: TextView = itemView.findViewById(R.id.roleTV)
        val emailTV: TextView = itemView.findViewById(R.id.emailTV)
        val phoneTV: TextView = itemView.findViewById(R.id.phoneTV)
        val viewUserBT: Button = itemView.findViewById(R.id.viewUserBT)
    }

    interface OnItemClickListeners {
        fun onClick(article:RecentData,position: Int)
    }

    fun setClickListeners(onItemClick:OnItemClickListeners) {
        onItemClickListner = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recently_registered_users_layout, parent, false)
        return UsersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.nameTV.text = currentItem.nameTV
        holder.roleTV.text = currentItem.roleTV
        holder.emailTV.text = currentItem.emailTV
        holder.phoneTV.text = currentItem.phoneTV
        holder.viewUserBT.setOnClickListener {
            onItemClickListner?.onClick(currentItem,position)
        }
    }

    override fun getItemCount() = userList.size
}