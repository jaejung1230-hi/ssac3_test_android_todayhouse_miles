package com.example.risingproject.src.detailStory.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerHashtagTextBinding
import com.example.risingproject.databinding.RecyclerItemFilterHorizontalColorBinding
import com.example.risingproject.databinding.RecyclerTagItemsBinding
import com.example.risingproject.src.detailInto.DetailInfoActivity
import com.example.risingproject.src.detailStory.models.idAndPic
import com.example.risingproject.src.detailStory.models.keywords
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment
import com.example.risingproject.util.GlobalFunctions

class HorizontalItemTagAdapter(private val context: Context, private val items: List<idAndPic>): RecyclerView.Adapter<HorizontalItemTagAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalItemTagAdapter.ViewHolder {
        val binding = RecyclerTagItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalItemTagAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(private var binding: RecyclerTagItemsBinding) : RecyclerView.ViewHolder(binding.root){
        val tempItmePic = listOf<Int>(R.drawable.temp_item_1, R.drawable.temp_item_2, R.drawable.temp_item_3, R.drawable.temp_item_4,
            R.drawable.temp_item_5, R.drawable.temp_item_6, R.drawable.temp_item_7)

        fun bind(context: Context, item: idAndPic){
            binding.containerTag.setOnClickListener {
                val intent = Intent(context, DetailInfoActivity::class.java)
                intent.putExtra("itemId",item.id)
                context.startActivity(intent)
            }

            Glide.with(context)
                .load(tempItmePic[position%7])
                .error(R.drawable.temp_item_1)
                .into(binding.imgTag)

        }
    }
}