package com.example.risingproject.src.allReview

import android.os.Bundle
import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityAllReviewBinding

class AllReviewActivity : BaseActivity<ActivityAllReviewBinding>(ActivityAllReviewBinding::inflate), AllReviewAcitivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        AllReviewService(this).tryGetAllReview(ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,0),intent.getIntExtra("itemId",0))
    }

    override fun onGetAllReviewSuccess(response: Any) {
        Log.d("test",response.toString())
    }

    override fun onGetAllReviewFailure(message: String) {
        Log.d("test",message)
    }
}