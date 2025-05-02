package com.example.minerush.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.RecentData
import com.example.minerush.R

class RecentUserAdapter(
    private val userList: List<RecentData>
) : RecyclerView.Adapter<RecentUserAdapter.UsersViewHolder>() {

    private var listener: OnItemClickListeners? = null

    interface OnItemClickListeners {
        fun onClick(applicant: RecentData, position: Int)
    }

    fun setClickListeners(listener: OnItemClickListeners) {
        this.listener = listener
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.nameDisplayTV)
        val roleTV: TextView = itemView.findViewById(R.id.userRoleDisplayTV)
        val emailTV: TextView = itemView.findViewById(R.id.emailDisplayTV)
        val phoneTV: TextView = itemView.findViewById(R.id.phoneDisplayTV)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener?.onClick(userList[position], position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recently_registered_users_layout, parent, false)
        return UsersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = userList[position]
        holder.nameTV.text = user.name
        holder.roleTV.text = user.role
        holder.emailTV.text = user.email
        holder.phoneTV.text = user.phone
    }

    override fun getItemCount() = userList.size
}
