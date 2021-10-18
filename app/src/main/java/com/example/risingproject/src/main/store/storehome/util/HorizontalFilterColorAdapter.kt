package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalColorBinding
import com.example.risingproject.databinding.RecyclerItemForHorizontalBinding

class HorizontalFilterColorAdapter(private val context: Context, private val items: List<String>): RecyclerView.Adapter<HorizontalFilterColorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalFilterColorAdapter.ViewHolder {
        val binding = RecyclerItemFilterHorizontalColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalFilterColorAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemFilterHorizontalColorBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: String){

        }
    }
}