package com.example.risingproject.src.detailInto.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerReviewsBinding
import com.example.risingproject.src.detailInto.DetailInfoActivity
import com.example.risingproject.src.detailInto.models.reviewList

class HorizontalReviewsAdapter(private val context: Context, private val items: List<reviewList>, val filterItemClick : FilterItemClick): RecyclerView.Adapter<HorizontalReviewsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalReviewsAdapter.ViewHolder {
        val binding = RecyclerReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalReviewsAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position], position, filterItemClick)
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(private var binding: RecyclerReviewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: reviewList, position : Int , filterItemClick : FilterItemClick){
            if (position == DetailInfoActivity.SelectedNum.selectedNum){
                binding.cardImgReview.strokeWidth = 5
            }
            else{
                binding.cardImgReview.strokeWidth = 0
            }
            binding.cardImgReview.setOnClickListener {
                DetailInfoActivity.SelectedNum.selectedNum = position
                filterItemClick.getSelectedItem(position)
                notifyDataSetChanged()
            }

            Glide.with(context).load(item.photo)
                .error(R.drawable.img_upload_pic)
                .into(binding.imgReview)
        }
    }
}