package com.example.risingproject.src.allReview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityAllReviewBinding
import com.example.risingproject.src.allReview.models.AllReviewResponse
import com.example.risingproject.src.allReview.util.ReviewsAdapter

class AllReviewActivity : BaseActivity<ActivityAllReviewBinding>(ActivityAllReviewBinding::inflate), AllReviewAcitivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        AllReviewService(this).tryGetAllReview(ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,0),intent.getIntExtra("itemId",1))
    }

    @SuppressLint("SetTextI18n")
    override fun onGetAllReviewSuccess(response: AllReviewResponse) {
        Log.d("AllReviewActivity",response.result.toString())
        val sum =  response.result[0].totalRate[0].`1points` + response.result[0].totalRate[0].`1points` + response.result[0].totalRate[0].`2points` + response.result[0].totalRate[0].`3points` + response.result[0].totalRate[0].`4points` + response.result[0].totalRate[0].`5points`
        binding.titleToolbar.text = "리뷰"+sum.toString()

        if(response.result[0].totalRate[0].`5points` != 0){
            binding.tv5pointCount.text = response.result[0].totalRate[0].`5points`.toString()
            binding.progressBar5point.progress = ((response.result[0].totalRate[0].`5points`.toFloat()/sum)*100).toInt()
        }
        if(response.result[0].totalRate[0].`4points` != 0){
            binding.tv4pointCount.text = response.result[0].totalRate[0].`4points`.toString()
            binding.progressBar4point.progress = ((response.result[0].totalRate[0].`4points`.toFloat()/sum)*100).toInt()
        }
        if(response.result[0].totalRate[0].`3points` != 0){
            binding.tv3pointCount.text = response.result[0].totalRate[0].`3points`.toString()
            binding.progressBar3point.progress = ((response.result[0].totalRate[0].`3points`.toFloat()/sum)*100).toInt()
        }
        if(response.result[0].totalRate[0].`2points` != 0){
            binding.tv2pointCount.text = response.result[0].totalRate[0].`2points`.toString()
            binding.progressBar2point.progress = ((response.result[0].totalRate[0].`2points`.toFloat()/sum)*100).toInt()
        }
        if(response.result[0].totalRate[0].`1points` < 1){
            binding.tv1pointCount.text = response.result[0].totalRate[0].`1points`.toString()
            binding.progressBar1point.progress = ((response.result[0].totalRate[0].`1points`.toFloat()/sum)*100).toInt()
        }

        binding.tvItemRateBig.text =  String.format("%.1f", response.result[0].totalRate[0].avgOfRate)
        binding.ratingbarBig.progress = ((response.result[0].totalRate[0].reviewAvg/5)*100).toInt()

        binding.recyclerMyReview.layoutManager = LinearLayoutManager(this)
        binding.recyclerMyReview.adapter = ReviewsAdapter(this,response.result[2].Reviews)

    }

    override fun onGetAllReviewFailure(message: String) {
        Log.d("test",message)
    }
}