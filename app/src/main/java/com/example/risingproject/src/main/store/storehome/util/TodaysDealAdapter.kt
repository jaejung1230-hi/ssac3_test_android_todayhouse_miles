package com.example.risingproject.src.main.store.storehome.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingproject.databinding.RecyclerItemForTodayBinding
import com.example.risingproject.src.main.store.storehome.models.ResultItemAll
import android.os.CountDownTimer
import android.util.Log
import com.bumptech.glide.Glide
import com.example.risingproject.R
import com.example.risingproject.src.detailInto.DetailInfoActivity
import java.text.SimpleDateFormat
import java.util.*


class TodaysDealAdapter(private val context: Context, private val items: List<ResultItemAll>): RecyclerView.Adapter<TodaysDealAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaysDealAdapter.ViewHolder {
        val binding = RecyclerItemForTodayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodaysDealAdapter.ViewHolder, position: Int) {
        holder.bind(context,items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private var binding: RecyclerItemForTodayBinding) : RecyclerView.ViewHolder(binding.root){
        var timer1 : CountDownTimer
        lateinit var currentVal : String


        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormatH = SimpleDateFormat("HH", Locale("ko", "KR"))
        val dateFormatM = SimpleDateFormat("mm", Locale("ko", "KR"))
        val dateFormatS = SimpleDateFormat("ss", Locale("ko", "KR"))
        val H = dateFormatH.format(date).toLong() * 1000 * 60 * 60
        val M = dateFormatM.format(date).toLong() * 1000 * 60
        val S = dateFormatS.format(date).toLong() * 1000


        init {
            timer1 = object : CountDownTimer(86400000-(H+M+S), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    var seconds = (millisUntilFinished / 1000).toInt()
                    val hours = seconds / (60 * 60)
                    val tempMint = seconds - hours * 60 * 60
                    val minutes = tempMint / 60
                    seconds = tempMint - minutes * 60
                    currentVal =
                        String.format("%02d", hours) + ":" + String.format(
                            "%02d",
                            minutes
                        ) + ":" + String.format("%02d", seconds)
                    setTime(currentVal)
                }

                override fun onFinish() {
                    setTime("00:00:00")
                }
            }.start()
        }

        fun setTime(leftTime : String){
            binding.tvLeftTime.text = leftTime
        }

        fun bind(context: Context, item: ResultItemAll){

            binding.containerToday.setOnClickListener {
                val intent = Intent(context, DetailInfoActivity::class.java)
                intent.putExtra("itemId",item.itemId)
                context.startActivity(intent)
            }

            Glide.with(context).load(item.mainPic)
                .error(R.drawable.temp_item_1)
                .into(binding.imgItemToday)

            binding.tvCompanyNameToday.text = item.companyName
            binding.tvItemTitleToday.text = item.itemName
            binding.tvItemRateToday.text = item.reviewRate.toString()
            binding.tvItemReviewsToday.text = item.numOfReviews.toString()
            binding.tvItemPercentToday.text = item.percenttage.toString()
            binding.tvItemPriceToday.text = item.salePrice.toString()

            if(item.percenttage >= 50){
                binding.tvItemSpecialPriceToday.visibility = View.VISIBLE
            }
        }
    }
}