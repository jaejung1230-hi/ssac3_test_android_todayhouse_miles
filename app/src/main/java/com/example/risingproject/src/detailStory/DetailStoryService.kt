package com.example.risingproject.src.detailStory

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.StoreCategoryRetrofitInterface
import com.example.risingproject.src.main.store.storehome.StoreHomeRetrofitInterface
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemRequest
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailStoryService(val view :DetailStoryAcitivityView){
    fun tryGetStory(userId: Int, storyId: Int){
        val detailStoryRetrofitInterface = ApplicationClass.sRetrofit.create(DetailStoryRetrofitInterface::class.java)
        detailStoryRetrofitInterface.getDetailStory(userId, storyId).enqueue(object : Callback<Any>{
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.d("test",response.toString())
                view.onGetDetailStorySuccess(response.body() as Any)
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                view.onGetDetailStoryFailure(t.message ?: "통신 오류")
            }

        })
    }
}