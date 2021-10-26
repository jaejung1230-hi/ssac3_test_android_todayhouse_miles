package com.example.risingproject.src.detailStory

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.detailStory.models.GetDetailStoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailStoryService(val view :DetailStoryAcitivityView){
    fun tryGetStory(userId: Int, storyId: Int){
        val detailStoryRetrofitInterface = ApplicationClass.sRetrofit.create(DetailStoryRetrofitInterface::class.java)
        detailStoryRetrofitInterface.getDetailStory(userId, storyId).enqueue(object : Callback<GetDetailStoryResponse>{
            override fun onResponse(call: Call<GetDetailStoryResponse>, response: Response<GetDetailStoryResponse>) {
                Log.d("test",response.toString())
                view.onGetDetailStorySuccess(response.body() as GetDetailStoryResponse)
            }

            override fun onFailure(call: Call<GetDetailStoryResponse>, t: Throwable) {
                view.onGetDetailStoryFailure(t.message ?: "통신 오류")
            }

        })
    }
}