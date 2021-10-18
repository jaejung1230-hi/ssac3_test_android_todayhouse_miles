package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalTextBinding
import com.example.risingproject.databinding.RecyclerItemForHorizontalBinding

class HorizontalTextColorAdapter(private val context: Context, private val items: List<String>): RecyclerView.Adapter<HorizontalTextColorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalTextColorAdapter.ViewHolder {
        val binding = RecyclerItemFilterHorizontalTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalTextColorAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemFilterHorizontalTextBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: String){
            binding.filterCategoryHorizontalText.text = item
        }
    }
}