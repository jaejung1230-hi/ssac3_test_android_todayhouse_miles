package com.example.risingproject.src.detailInto.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalColorBinding
import com.example.risingproject.databinding.RecyclerReviewInfoBinding
import com.example.risingproject.databinding.RecyclerReviewsBinding
import com.example.risingproject.src.detailInto.models.reviewList
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.util.GlobalFunctions

class ReviewsAdapter(private val context: Context, private val items: List<reviewList>): RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    private var selectedNum = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ViewHolder {
        val binding = RecyclerReviewInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(private var binding: RecyclerReviewInfoBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(context: Context, item: reviewList){

            binding.tvReviewerName.text = item.userName
            binding.ratingberReview.rating = item.reviewAvg.toFloat()
            binding.tvUploadtime.text = "    "+item.concat
            binding.tvKind.text = "사이즈/색상: 퀸 화이트 / 구성 풀세트"
            binding.tvContext.text = item.context

            Glide.with(context).load(item.photo)
                .error(R.drawable.img_upload_pic)
                .into(binding.imgReview)
        }
    }
}