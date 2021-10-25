package com.example.risingproject.src.myReview

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.myReview.models.MyReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyReviewService(val view :MyReviewAcitivityView){
    fun tryGetMyReview(userId: Int){
        val myListRetrofitInterface = ApplicationClass.sRetrofit.create(MyListRetrofitInterface::class.java)
        myListRetrofitInterface.getMyReview(userId).enqueue(object : Callback<MyReviewResponse>{
            override fun onResponse(call: Call<MyReviewResponse>, response: Response<MyReviewResponse>) {
                Log.d("MyReviewService",response.toString())
                view.onGetMyReviewSuccess(response.body() as MyReviewResponse)
            }

            override fun onFailure(call: Call<MyReviewResponse>, t: Throwable) {
                view.onGetMyReviewFailure(t.message ?: "통신 오류")
            }

        })
    }
}