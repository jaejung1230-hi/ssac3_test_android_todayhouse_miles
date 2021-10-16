package com.example.risingproject.src.main.store.storehome.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.R

class AdsViewPagerAdapter(itemList: List<Int>) : RecyclerView.Adapter<AdsViewPagerAdapter.PagerViewHolder>() {
    var item = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.abs.setImageResource(item[position%(item.size)])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.view_pager_ads, parent, false)){
        val abs: ImageView = itemView.findViewById(R.id.img_abs)
    }
}