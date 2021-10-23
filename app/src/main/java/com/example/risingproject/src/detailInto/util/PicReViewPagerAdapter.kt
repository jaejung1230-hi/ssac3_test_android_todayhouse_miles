package com.example.risingproject.src.detailInto.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.src.detailInto.models.reviewList

class PicReViewPagerAdapter(val context : Context, itemList: List<reviewList>) : RecyclerView.Adapter<PicReViewPagerAdapter.PagerViewHolder>() {
    var item = itemList

    val tempItmePic = listOf<Int>(R.drawable.temp_item_1, R.drawable.temp_item_2, R.drawable.temp_item_3, R.drawable.temp_item_4,
        R.drawable.temp_item_5, R.drawable.temp_item_6, R.drawable.temp_item_7)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.tv_reviewer.text = item[position].userName
        holder.tv_num_now.text = (position+1).toString()
        holder.tv_num_total.text = item.size.toString()

        Glide.with(context).load(item[position].photo)
            .error(tempItmePic[position])
            .into(holder.img_review)

    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.view_pager_reviews, parent, false)){
        val img_review: ImageView = itemView.findViewById(R.id.img_review)
        val tv_reviewer: TextView = itemView.findViewById(R.id.tv_reviewer)
        val tv_num_now: TextView = itemView.findViewById(R.id.tv_num_now)
        val tv_num_total: TextView = itemView.findViewById(R.id.tv_num_total)
    }
}