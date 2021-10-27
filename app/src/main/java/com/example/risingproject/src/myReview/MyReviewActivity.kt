package com.example.risingproject.src.myReview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityMyReviewBinding
import com.example.risingproject.src.myReview.models.MyReviewResponse
import com.example.risingproject.src.myReview.util.MyReviewsAdapter

class MyReviewActivity : BaseActivity<ActivityMyReviewBinding>(ActivityMyReviewBinding::inflate), MyReviewAcitivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoadingDialog(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        MyReviewService(this).tryGetMyReview(ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,1))
    }

    override fun onGetMyReviewSuccess(response: MyReviewResponse) {
        dismissLoadingDialog()
        Log.d("MyReviewService",response.result.toString())

        binding.recyclerMyReview.layoutManager = LinearLayoutManager(this)
        binding.recyclerMyReview.adapter = MyReviewsAdapter(this,response.result)
    }

    override fun onGetMyReviewFailure(message: String) {
        dismissLoadingDialog()
        Log.d("MyReviewService",message)
    }
}