package com.example.risingproject.src.detailStory

import android.os.Bundle
import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityDetailStoryBinding

class DetailStortActivity : BaseActivity<ActivityDetailStoryBinding>(ActivityDetailStoryBinding::inflate),DetailStoryAcitivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailStoryService(this).tryGetStory(ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,0),intent.getIntExtra("storyId",1))
    }

    override fun onGetDetailStorySuccess(response: Any) {
        Log.d("DetailStortActivity",response.toString())
    }

    override fun onGetDetailStoryFailure(message: String) {
        Log.d("DetailStortActivity",message)
    }
}