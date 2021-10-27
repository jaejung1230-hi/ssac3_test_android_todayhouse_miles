package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerItemForBigBinding
import com.example.risingproject.src.detailInto.DetailInfoActivity
import com.example.risingproject.src.main.home.HomeFragment
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.src.main.store.storehome.StoreHomeFragemt
import com.example.risingproject.src.main.store.storehome.models.ResultItemUseFilter
import com.example.risingproject.src.main.store.storehome.models.temp
import java.text.DecimalFormat

class StoreCategoryCategoryFilterGridViewAdapter(private var context: Context, val items: ArrayList<ResultItemUseFilter>): RecyclerView.Adapter<StoreCategoryCategoryFilterGridViewAdapter.ViewHolder>() {

    fun add(addlist: ArrayList<ResultItemUseFilter>){
        for(add in addlist){
            items.add(add)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreCategoryCategoryFilterGridViewAdapter.ViewHolder {
        val binding = RecyclerItemForBigBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreCategoryCategoryFilterGridViewAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemForBigBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ResultItemUseFilter){
            binding.containerBig.setOnClickListener {
                val intent = Intent(context, DetailInfoActivity::class.java)
                intent.putExtra("itemId",item.itemId)
                context.startActivity(intent)
            }

            binding.tvCompanyNameBig.text = item.companyName
            binding.tvItemTitleBig.text = item.itemName
            binding.tvItemPercentBig.text = item.percenttage.toString()
            val t_dec_up = DecimalFormat("###,###,###")
            binding.tvItemPriceBig.text = t_dec_up.format(item.salePrice)
            binding.tvItemRateBig.text = item.reviewRate.toString()
            binding.tvItemReviewsBig.text = item.numOfReviews.toString()

            Glide.with(context).load(item.mainPic)
                .error(R.drawable.temp_item_1)
                .into(binding.imgItemBig)

            if(item.deliveryFee == "N"){
                binding.tvItemDeliveryFreeBig.visibility = View.VISIBLE
            }
            if(item.percenttage >= 50){
                binding.tvItemSpecialPriceBig.visibility = View.VISIBLE
            }
        }
    }
}