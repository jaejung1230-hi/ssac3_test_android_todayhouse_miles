package com.example.risingproject.src.detailStory

import com.example.risingproject.src.detailStory.models.GetDetailStoryResponse
import com.example.risingproject.src.main.home.homemain.models.getPicResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DetailStoryRetrofitInterface {
    @GET("app/userId/{userId}/stories?")
    fun getDetailStory(@Path("userId") userId : Int,
                       @Query("storyId") storyId : Int
    ) : Call<GetDetailStoryResponse>

    @GET("app/extras/stories")
    fun getPic() : Call<getPicResponse>
}