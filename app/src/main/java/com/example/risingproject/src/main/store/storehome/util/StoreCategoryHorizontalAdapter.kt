package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerItemForHorizontalBinding
import com.example.risingproject.src.detailInto.DetailInfoActivity
import com.example.risingproject.src.main.store.storehome.models.ResultItem
import java.text.DecimalFormat

class StoreCategoryHorizontalAdapter(private val context: Context, private val items: List<ResultItem>): RecyclerView.Adapter<StoreCategoryHorizontalAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreCategoryHorizontalAdapter.ViewHolder {
        val binding = RecyclerItemForHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreCategoryHorizontalAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position],position)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemForHorizontalBinding) : RecyclerView.ViewHolder(binding.root){
        val tempItmePic = listOf<Int>(R.drawable.temp_item_1, R.drawable.temp_item_2, R.drawable.temp_item_3, R.drawable.temp_item_4,
            R.drawable.temp_item_5, R.drawable.temp_item_6, R.drawable.temp_item_7)

        fun bind(context: Context, item: ResultItem, position: Int){
            binding.tvCompanyName.text = item.companyName
            binding.tvItemTitle.text = item.itemName
            binding.tvItemPercent.text = item.percenttage.toString()
            val t_dec_up = DecimalFormat("###,###,###")
            binding.tvItemPrice.text = t_dec_up.format(item.sale)
            binding.tvItemRate.text = item.reviewRate.toString()
            binding.tvItemReviews.text = item.numOfReviews.toString()

            /*Glide.with(context).load(item.)
                .error(R.drawable.temp_item_1)
                .into(binding.imgItem)*/

            binding.imgItem.setImageResource(tempItmePic[position%7])

            binding.containerHorizontal.setOnClickListener {
                val intent = Intent(context, DetailInfoActivity::class.java)
                intent.putExtra("itemId",item.itemId)
                context.startActivity(intent)
            }

            if(item.deliveryFee == "N"){
                binding.tvItemDeliveryFree.visibility = View.VISIBLE
            }
            if(item.percenttage >= 50){
                binding.tvItemSpecialPrice.visibility = View.VISIBLE
            }

        }
    }
}