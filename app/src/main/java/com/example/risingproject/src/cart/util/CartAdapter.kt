package com.example.risingproject.src.cart.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerAllReviewInfoBinding
import com.example.risingproject.databinding.RecyclerCartBinding
import com.example.risingproject.databinding.RecyclerReviewsBinding
import com.example.risingproject.src.allReview.models.ReviewsItem
import com.example.risingproject.src.cart.model.ItemsItem
import com.example.risingproject.src.detailInto.DetailInfoActivity
import com.example.risingproject.src.detailInto.models.reviewList

class CartAdapter(private val context: Context, private val items: List<ItemsItem>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val binding = RecyclerCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(private var binding: RecyclerCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ItemsItem){
            binding.tvWhere.text = item.deliveryFrom
            binding.tvItemName.text = item.ItemName
            binding.tvDel.text = item.deliveryFee
            binding.tvPost.text = "일반택배"

            binding.tvItemName2.text = item.optionName

            binding.tvItemPrice.text = item.price
            binding.tvItemPrice2.text = item.price

            Glide.with(context).load(item.itemId)
                .error(R.drawable.img_upload_pic)
                .into(binding.imgReview)
        }
    }
}