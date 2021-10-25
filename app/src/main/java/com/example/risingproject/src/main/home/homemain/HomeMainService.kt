package com.example.risingproject.src.uploadpic.util

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.main.home.homemain.HomeMainFragmentView
import com.example.risingproject.src.main.home.homemain.HomeMainRetrofitInterface
import com.example.risingproject.src.main.home.homemain.models.getPicResponse
import com.example.risingproject.src.review.models.WriteReviewRequest
import com.example.risingproject.src.uploadpic.UploadPicActivityView
import com.example.risingproject.src.uploadpic.UploadPicRetrofitInterface
import com.example.risingproject.src.uploadpic.models.UploadPicRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeMainService(val view : HomeMainFragmentView) {
    fun tryPostPic(){
        val homeMainRetrofitInterface = ApplicationClass.sRetrofit.create(HomeMainRetrofitInterface::class.java)
        homeMainRetrofitInterface.getPic().enqueue(object : Callback<getPicResponse> {

            override fun onResponse(call: Call<getPicResponse>, response: Response<getPicResponse>) {
                Log.d("testaa",response.body()?.message.toString())
                Log.d("testaa",response.body()?.code.toString())
                Log.d("testaa",response.body()?.isSuccess.toString())
                view.onGetPicsSuccess(response.body() as getPicResponse)
            }

            override fun onFailure(call: Call<getPicResponse>, t: Throwable) {
                Log.d("testaa",t.toString())
                view.onGetPicsFailure(t.message ?: "통신 오류")
            }
        })
    }

}