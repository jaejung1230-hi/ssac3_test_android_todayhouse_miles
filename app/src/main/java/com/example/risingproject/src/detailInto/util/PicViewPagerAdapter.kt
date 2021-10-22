package com.example.risingproject.src.detailInto.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.R

class PicViewPagerAdapter(itemList: List<Int>) : RecyclerView.Adapter<PicViewPagerAdapter.PagerViewHolder>() {
    var item = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.abs.setImageResource(item[position])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.view_pager_ads, parent, false)){
        val abs: ImageView = itemView.findViewById(R.id.img_abs)
    }
}