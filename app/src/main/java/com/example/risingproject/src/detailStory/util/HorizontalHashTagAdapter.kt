package com.example.risingproject.src.detailStory.util

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerHashtagTextBinding
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalColorBinding
import com.example.risingproject.src.detailStory.models.keywords
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.util.GlobalFunctions

class HorizontalHashTagAdapter(private val context: Context, private val items: List<keywords>): RecyclerView.Adapter<HorizontalHashTagAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalHashTagAdapter.ViewHolder {
        val binding = RecyclerHashtagTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalHashTagAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(private var binding: RecyclerHashtagTextBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: keywords){
            binding.tvKetword.text = "# " + item.content

        }
    }
}