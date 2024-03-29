package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerItemForHorizontalBinding
import com.example.risingproject.src.detailInto.DetailInfoActivity
import com.example.risingproject.src.main.store.storehome.models.ResultItemAll
import java.text.DecimalFormat

class StoreHomeRecordAdapter(private val context: Context, private val items: List<ResultItemAll>): RecyclerView.Adapter<StoreHomeRecordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreHomeRecordAdapter.ViewHolder {
        val binding = RecyclerItemForHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreHomeRecordAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemForHorizontalBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ResultItemAll){
            binding.containerHorizontal.setOnClickListener {
                val intent = Intent(context, DetailInfoActivity::class.java)
                intent.putExtra("itemId",item.itemId)
                context.startActivity(intent)
            }

            Log.d("mainPic",item.mainPic.toString())

            Glide.with(context).load(item.mainPic)
                .error(R.drawable.temp_item_1)
                .into(binding.imgItem)

            binding.tvCompanyName.text = item.companyName
            binding.tvItemTitle.text = item.itemName
            binding.tvItemPercent.text = item.percenttage.toString()
            val t_dec_up = DecimalFormat("###,###,###")
            binding.tvItemPrice.text = t_dec_up.format(item.sale)
            binding.tvItemRate.text = item.reviewRate.toString()
            binding.tvItemReviews.text = item.numOfReviews.toString()

            if(item.percenttage >= 50){
                binding.tvItemSpecialPrice.visibility = View.VISIBLE
            }

        }
    }
}