package com.example.risingproject.src.detailStory

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.detailStory.models.GetDetailStoryResponse
import com.example.risingproject.src.main.home.homemain.HomeMainRetrofitInterface
import com.example.risingproject.src.main.home.homemain.models.getPicResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailStoryService(val view :DetailStoryAcitivityView){
    fun tryGetStory(userId: Int, storyId: Int){
        val detailStoryRetrofitInterface = ApplicationClass.sRetrofit.create(DetailStoryRetrofitInterface::class.java)
        detailStoryRetrofitInterface.getDetailStory(userId, storyId).enqueue(object : Callback<GetDetailStoryResponse>{
            override fun onResponse(call: Call<GetDetailStoryResponse>, response: Response<GetDetailStoryResponse>) {
                Log.d("DetailStortActivity",response.toString())
                view.onGetDetailStorySuccess(response.body() as GetDetailStoryResponse)
            }

            override fun onFailure(call: Call<GetDetailStoryResponse>, t: Throwable) {
                view.onGetDetailStoryFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryGetPic(){
        val detailStoryRetrofitInterface = ApplicationClass.sRetrofit.create(DetailStoryRetrofitInterface::class.java)
        detailStoryRetrofitInterface.getPic().enqueue(object : Callback<getPicResponse> {

            override fun onResponse(call: Call<getPicResponse>, response: Response<getPicResponse>) {
                Log.d("DetailStortActivity",response.body()?.message.toString())
                Log.d("DetailStortActivity",response.body()?.code.toString())
                Log.d("DetailStortActivity",response.body()?.isSuccess.toString())
                view.onGetPicsSuccess(response.body() as getPicResponse)
            }

            override fun onFailure(call: Call<getPicResponse>, t: Throwable) {
                Log.d("testaa",t.toString())
                view.onGetPicsFailure(t.message ?: "통신 오류")
            }
        })
    }
}