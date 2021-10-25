package com.example.risingproject.src.main.home.homemain.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.databinding.RecyclerBestPicBinding
import com.example.risingproject.src.detailStory.DetailStortActivity
import com.example.risingproject.src.main.home.homemain.models.ResultPic

class BestPicRecyclerAdapter(private val context: Context, private val items: List<ResultPic>): RecyclerView.Adapter<BestPicRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestPicRecyclerAdapter.ViewHolder {
        val binding = RecyclerBestPicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestPicRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position], position)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerBestPicBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(context: Context, item: ResultPic, pos: Int){
            Glide.with(context)
                .load(item.photo)
                .error(R.drawable.temp_item_1)
                .into(binding.imgPic)

            binding.tvPicNickname.text = item.userNickName

            if(pos <= 2){
                binding.bgTvFlag.setBackgroundResource(R.drawable.img_flag1)
            }else{
                binding.bgTvFlag.setBackgroundResource(R.drawable.img_flag2)
            }
            binding.tvFlag.text = (pos+1).toString()
            binding.containerStory.setOnClickListener {
                val intent = Intent(context,DetailStortActivity::class.java)
                intent.putExtra("storyId",item.storyId)
                context.startActivity(intent)
            }
        }
    }
}