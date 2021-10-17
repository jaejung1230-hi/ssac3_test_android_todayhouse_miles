package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemForHorizontalBinding

class StoreCategoryHorizontalAdapter(private val context: Context, private val items: List<Int>): RecyclerView.Adapter<StoreCategoryHorizontalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreCategoryHorizontalAdapter.ViewHolder {
        val binding = RecyclerItemForHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreCategoryHorizontalAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemForHorizontalBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(context: Context, item: Int){


        }
    }
}