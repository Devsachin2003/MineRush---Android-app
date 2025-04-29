package com.example.minerush.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minerush.DataClass.FaqsData
import com.example.minerush.databinding.SectionItemBinding

class SectionAdapter(private val sectionList: List<Pair<String, List<FaqsData>>>) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    class SectionViewHolder(val binding: SectionItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding = SectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val (sectionTitle, faqs) = sectionList[position]
        holder.binding.sectionTitle.text = sectionTitle

        // Set child RecyclerView
        val childAdapter = FaqsAdapter(faqs)
        holder.binding.faqsRecyclerView.adapter = childAdapter
        holder.binding.faqsRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
    }

    override fun getItemCount() = sectionList.size
}
