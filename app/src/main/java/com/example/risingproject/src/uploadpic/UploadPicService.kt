package com.example.risingproject.src.uploadpic.util

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.review.models.WriteReviewRequest
import com.example.risingproject.src.uploadpic.UploadPicActivityView
import com.example.risingproject.src.uploadpic.UploadPicRetrofitInterface
import com.example.risingproject.src.uploadpic.models.UploadPicRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadPicService(val view : UploadPicActivityView) {
    fun tryPostPic(userId : Int,  uploadPicRequest: UploadPicRequest){
        val uploadPicRetrofitInterface = ApplicationClass.sRetrofit.create(UploadPicRetrofitInterface::class.java)
        uploadPicRetrofitInterface.postReview(userId, uploadPicRequest).enqueue(object : Callback<BaseResponse> {

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("testaa",response.body()?.message.toString())
                Log.d("testaa",response.body()?.code.toString())
                Log.d("testaa",response.body()?.isSuccess.toString())
                view.onPostPicSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("testaa",t.toString())
                view.onPostPicFailure(t.message ?: "통신 오류")
            }
        })
    }

}