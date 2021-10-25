package com.example.risingproject.src.myReview.util

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
import com.example.risingproject.databinding.RecyclerMyReviewInfoBinding
import com.example.risingproject.databinding.RecyclerReviewInfoBinding
import com.example.risingproject.databinding.RecyclerReviewsBinding
import com.example.risingproject.src.detailInto.models.reviewList
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.src.myReview.models.ResultReview
import com.example.risingproject.util.GlobalFunctions

class MyReviewsAdapter(private val context: Context, private val items: List<ResultReview>): RecyclerView.Adapter<MyReviewsAdapter.ViewHolder>() {

    private var selectedNum = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReviewsAdapter.ViewHolder {
        val binding = RecyclerMyReviewInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyReviewsAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(private var binding: RecyclerMyReviewInfoBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(context: Context, item: ResultReview){

            //binding.tvReviewerName.text = item.userName
            binding.ratingberReview.rating = item.reviewAvg
            binding.tvUploadtime.text = item.createdAt
            binding.tvKind.text = "사이즈/색상: 퀸 화이트 / 구성 풀세트"
            binding.tvContext.text = item.context

            Glide.with(context).load(item.photoUrl)
                .error(R.drawable.img_upload_pic)
                .into(binding.imgReview)
        }
    }
}