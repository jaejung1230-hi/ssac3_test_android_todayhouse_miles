package com.example.risingproject.src.detailStory.util

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerHashtagTextBinding
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalColorBinding
import com.example.risingproject.databinding.RecyclerItemForSameStyleBinding
import com.example.risingproject.databinding.RecyclerTagItemsBinding
import com.example.risingproject.src.detailStory.models.keywords
import com.example.risingproject.src.main.home.homemain.models.ResultPic
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.util.GlobalFunctions

class SamePicAdapter(private val context: Context, private val items: List<ResultPic>): RecyclerView.Adapter<SamePicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SamePicAdapter.ViewHolder {
        val binding = RecyclerItemForSameStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SamePicAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(private var binding: RecyclerItemForSameStyleBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ResultPic){
            Glide.with(context)
                .load(item.photo)
                .error(R.drawable.temp_item_1)
                .into(binding.imgItemBig)
            binding.tvItemTitleBig.text = item.userNickName
        }
    }
}