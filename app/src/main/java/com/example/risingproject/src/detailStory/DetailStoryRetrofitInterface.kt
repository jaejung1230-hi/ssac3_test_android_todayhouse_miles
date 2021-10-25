package com.example.risingproject.src.detailStory

import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DetailStoryRetrofitInterface {
    @GET("app/userId/{userId}/stories?")
    fun getDetailStory(@Path("userId") userId : Int,
                       @Query("storyId") storyId : Int
    ) : Call<Any>

}