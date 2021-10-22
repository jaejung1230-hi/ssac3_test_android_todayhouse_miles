package com.example.risingproject.src.review

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.review.models.WriteReviewRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteReviewService(val view : WriteReviewActivityView) {
    fun tryPostReview(userId : Int, body: MultipartBody.Part, writeReviewRequest: WriteReviewRequest){
        val signupOneRetrofitInterface = ApplicationClass.sRetrofit.create(WriteReviewRetrofitInterface::class.java)
        signupOneRetrofitInterface.postReview(userId, body, writeReviewRequest).enqueue(object : Callback<BaseResponse> {

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("testaa",response.body()?.message.toString())
                Log.d("testaa",response.body()?.code.toString())
                Log.d("testaa",response.body()?.isSuccess.toString())
                view.onPostWriteReviewSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("testaa",t.toString())
                view.onPostWriteReviewFailure(t.message ?: "통신 오류")
            }
        })
    }

}