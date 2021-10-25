package com.example.risingproject.src.uploadpic.addItemTag.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemForTagBinding
import com.example.risingproject.src.uploadpic.addItemTag.SelectTagClick
import com.example.risingproject.src.uploadpic.addItemTag.models.ResultItem

class DialogRecyclerAdapter(private val context: Context, private val items: List<ResultItem>, private val selectTagClick: SelectTagClick): RecyclerView.Adapter<DialogRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogRecyclerAdapter.ViewHolder {
        val binding = RecyclerItemForTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position], selectTagClick)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemForTagBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ResultItem, selectTagClick: SelectTagClick){
            //binding.imgItemTag
            binding.tvItemTag.text = item.itemName

            binding.btnItemTag.setOnClickListener {
                selectTagClick.getSelectedTag(item)
            }
        }
    }
}