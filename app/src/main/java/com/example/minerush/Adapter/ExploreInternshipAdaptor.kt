package com.android.minerush

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.ExploreInternData
import com.example.minerush.R

class ExploreInternshipAdaptor(private val userList: ArrayList<ExploreInternData>) : RecyclerView.Adapter<ExploreInternshipAdaptor.UsersViewHolder>() {

    private var onItemClickListener: OnItemClickListeners? = null

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val internshipRoleTV: TextView = itemView.findViewById(R.id.jobTitle)
        val companyNameTV: TextView = itemView.findViewById(R.id.txtCompanyName)
        val locationTV: TextView = itemView.findViewById(R.id.txtLocation)
        val modeTV: TextView = itemView.findViewById(R.id.modeType)
        val durationTV: TextView=itemView.findViewById((R.id.durationTV))
        val descriptionTV: TextView = itemView.findViewById(R.id.txtDescription)
        val applyBT: Button = itemView.findViewById(R.id.btnApply)
        val up: ImageView = itemView.findViewById(R.id.uparrow)
        val down: ImageView = itemView.findViewById(R.id.downarrow)
        val expandLay: LinearLayout = itemView.findViewById(R.id.expandLay)
    }

    interface OnItemClickListeners {
        fun onClick(article: ExploreInternData, position: Int)
    }

    fun setClickListeners(onItemClick: OnItemClickListeners) {
        onItemClickListener = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.explore_internship_items, parent, false)
        return UsersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = userList[position]

        // Set text for each field
        holder.internshipRoleTV.text = currentItem.title
        holder.companyNameTV.text = currentItem.company
        holder.locationTV.text = currentItem.location
        holder.modeTV.text = currentItem.mode
        holder.durationTV.text = currentItem.duration
        holder.descriptionTV.text = currentItem.description

        // Apply button click listener
        holder.applyBT.setOnClickListener {
            onItemClickListener?.onClick(currentItem, position)
        }

        // Set visibility based on isExpanded state
        holder.expandLay.visibility = if (currentItem.isExpanded) View.VISIBLE else View.GONE
        holder.down.visibility = if (currentItem.isExpanded) View.GONE else View.VISIBLE
        holder.up.visibility = if (currentItem.isExpanded) View.VISIBLE else View.GONE

        // Expand action
        holder.down.setOnClickListener {
            currentItem.isExpanded = true
            holder.expandLay.visibility = View.VISIBLE
            holder.down.visibility = View.GONE
            holder.up.visibility = View.VISIBLE
        }

        // Collapse action
        holder.up.setOnClickListener {
            currentItem.isExpanded = false
            holder.expandLay.visibility = View.GONE
            holder.down.visibility = View.VISIBLE
            holder.up.visibility = View.GONE
        }
    }

    override fun getItemCount() = userList.size
}
