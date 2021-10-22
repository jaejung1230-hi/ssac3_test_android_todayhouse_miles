package com.example.risingproject.src.detailInto.util

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalColorBinding
import com.example.risingproject.databinding.RecyclerReviewsBinding
import com.example.risingproject.src.detailInto.models.reviewList
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.util.GlobalFunctions

class HorizontalReviewsAdapter(private val context: Context, private val items: List<reviewList>): RecyclerView.Adapter<HorizontalReviewsAdapter.ViewHolder>() {

    private var selectedNum = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalReviewsAdapter.ViewHolder {
        val binding = RecyclerReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalReviewsAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position], position)
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(private var binding: RecyclerReviewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: reviewList, position : Int){
            if (position == selectedNum){
                binding.cardImgReview.strokeWidth = 5
            }
            else{
                binding.cardImgReview.strokeWidth = 0
            }
            binding.cardImgReview.setOnClickListener {
                selectedNum = position
                notifyDataSetChanged()
            }
        }
    }
}