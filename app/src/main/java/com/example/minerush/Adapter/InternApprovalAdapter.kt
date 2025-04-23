package com.android.minerush

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.NewInternApproveData
import com.example.minerush.R

class InternApprovalAdapter(private val articleList: ArrayList<NewInternApproveData>) : RecyclerView.Adapter<InternApprovalAdapter.UsersViewHolder>() {

    private var onItemClickListner:OnItemClickListeners? = null

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val emailTV: TextView = itemView.findViewById(R.id.emailTV)
        val phoneTV: TextView = itemView.findViewById(R.id.phoneTV)
        val areaOfInterestTV: TextView = itemView.findViewById(R.id.areaOfInterestTV)
        val viewUserBT: Button = itemView.findViewById(R.id.viewApplicantBT)
    }

    interface OnItemClickListeners {
        fun onClick(article:NewInternApproveData,position: Int)
    }

    fun setClickListeners(onItemClick:OnItemClickListeners) {
        onItemClickListner = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.new_intern_applications_approval_layout, parent, false)
        return UsersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = articleList[position]
        holder.nameTV.text = currentItem.nameTV
        holder.emailTV.text = currentItem.emailTV
        holder.phoneTV.text = currentItem.phoneTV
        holder.areaOfInterestTV.text = currentItem.areaOfInterestTV
        holder.viewUserBT.setOnClickListener {
            onItemClickListner?.onClick(currentItem,position)
        }

    }

    override fun getItemCount() = articleList.size
}