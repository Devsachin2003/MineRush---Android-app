package com.example.minerush.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.FaqsData
import com.example.minerush.databinding.FaqItemBinding

class FaqsAdapter(private val faqsList: List<FaqsData>) : RecyclerView.Adapter<FaqsAdapter.FaqViewHolder>() {

    class FaqViewHolder(val binding: FaqItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val binding = FaqItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FaqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        val faq = faqsList[position]
        holder.binding.questionTextView.text = faq.question
        holder.binding.answerTextView.text = faq.answer

        holder.binding.answerTextView.visibility = if (faq.isExpanded) View.VISIBLE else View.GONE

        holder.binding.questionTextView.setOnClickListener {
            faq.isExpanded = !faq.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = faqsList.size
}
