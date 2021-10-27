package com.example.risingproject.src.allReview.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerAllReviewInfoBinding
import com.example.risingproject.databinding.RecyclerReviewsBinding
import com.example.risingproject.src.allReview.models.ReviewsItem
import com.example.risingproject.src.detailInto.DetailInfoActivity
import com.example.risingproject.src.detailInto.models.reviewList

class ReviewsAdapter(private val context: Context, private val items: List<ReviewsItem>): RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ViewHolder {
        val binding = RecyclerAllReviewInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(private var binding: RecyclerAllReviewInfoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ReviewsItem){
            binding.tvReviewerName.text = item.userNickName
            binding.ratingberReview.progress = item.reviewAvg * 20
            binding.tvUploadtime.text = item.uploadTime
            binding.tvKind.text = "사이즈/색상: 퀸 화이트 / 구성 풀세트"
            binding.tvContext.text = item.context
            if(item.helpedNum != 0){
                binding.tvHelp2.text = item.helpedNum.toString() + "명에게 도움이 되었습니다."
            }else{
                binding.tvHelp2.visibility = View.INVISIBLE
            }
            Glide.with(context).load(item.photo)
                .error(R.drawable.img_upload_pic)
                .into(binding.imgReview)
        }
    }
}