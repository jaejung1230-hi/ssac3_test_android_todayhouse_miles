package com.example.risingproject.src.detailStory.util

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerHashtagTextBinding
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalColorBinding
import com.example.risingproject.databinding.RecyclerTagItemsBinding
import com.example.risingproject.src.detailStory.models.keywords
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.util.GlobalFunctions

class HorizontalTempAdapter(private val context: Context, private val items: List<Int>): RecyclerView.Adapter<HorizontalTempAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalTempAdapter.ViewHolder {
        val binding = RecyclerTagItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalTempAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(private var binding: RecyclerTagItemsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: Int){
            binding.imgTag.setImageResource(item)
            binding.cardImgReview.strokeWidth = 0

        }
    }
}