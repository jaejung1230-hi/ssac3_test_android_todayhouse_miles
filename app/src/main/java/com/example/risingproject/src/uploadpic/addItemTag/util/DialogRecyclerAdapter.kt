package com.example.risingproject.src.uploadpic.addItemTag.util

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalTextBinding
import com.example.risingproject.databinding.RecyclerItemForTagBinding
import com.example.risingproject.databinding.RecyclerItemTenFilterBinding
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.src.uploadpic.addItemTag.models.ResultItem

class DialogRecyclerAdapter(private val context: Context, private val items: List<ResultItem>): RecyclerView.Adapter<DialogRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogRecyclerAdapter.ViewHolder {
        val binding = RecyclerItemForTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemForTagBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ResultItem){
            //binding.imgItemTag
            binding.tvItemTag.text = item.itemName
        }
    }
}