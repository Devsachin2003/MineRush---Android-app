package com.android.minerush

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.InternApplicant
import com.example.minerush.R

class InternApprovalAdapter(
    private val applicantList: ArrayList<InternApplicant>
) : RecyclerView.Adapter<InternApprovalAdapter.ApplicantViewHolder>() {

    private var listener: OnItemClickListeners? = null

    interface OnItemClickListeners {
        fun onClick(applicant: InternApplicant, position: Int)
    }

    fun setClickListeners(onItemClick: OnItemClickListeners) {
        listener = onItemClick
    }

    inner class ApplicantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameDisplay: TextView = itemView.findViewById(R.id.nameDisplay)
        val emailDisplay: TextView = itemView.findViewById(R.id.emailDisplay)
        val phoneDisplay: TextView = itemView.findViewById(R.id.phoneDisplay)
        val degreeText: TextView = itemView.findViewById(R.id.degreeText)
        val yearTV: TextView = itemView.findViewById(R.id.yearTV)
        val viewUserBT: Button = itemView.findViewById(R.id.viewApplicantBT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_intern_applications_approval_layout, parent, false)
        return ApplicantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplicantViewHolder, position: Int) {
        val current = applicantList[position]
        holder.nameDisplay.text = current.name
        holder.emailDisplay.text = current.email
        holder.phoneDisplay.text = current.phone
        holder.degreeText.text = current.degree
        holder.yearTV.text = current.year

        holder.viewUserBT.setOnClickListener {
            listener?.onClick(current, position)
        }
    }

    override fun getItemCount(): Int = applicantList.size
}