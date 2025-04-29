package com.android.minerush

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.GlossaryData
import com.example.minerush.R

class GlossaryAdaptor(private val userList: ArrayList<GlossaryData>) : RecyclerView.Adapter<GlossaryAdaptor.UsersViewHolder>() {

    private var onItemClickListener: OnItemClickListeners? = null

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val glossaryTermTV: TextView = itemView.findViewById(R.id.glossaryTermTV)
        val definitionTV: TextView = itemView.findViewById(R.id.glossaryDefinitionTV)
        val up: ImageView = itemView.findViewById(R.id.uparrow)
        val down: ImageView = itemView.findViewById(R.id.downarrow)
        val expandLay: LinearLayout = itemView.findViewById(R.id.expandLay)
    }

    interface OnItemClickListeners {
        fun onClick(article: GlossaryData, position: Int)
    }

    fun setClickListeners(onItemClick: OnItemClickListeners) {
        onItemClickListener = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.glossary_items, parent, false)
        return UsersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = userList[position]

        // Set text for each field
        holder.glossaryTermTV.text = currentItem.term
        holder.definitionTV.text = currentItem.definition

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
    fun notifyItemChanged(glossaryList: ArrayList<GlossaryData>) {

    }
}
