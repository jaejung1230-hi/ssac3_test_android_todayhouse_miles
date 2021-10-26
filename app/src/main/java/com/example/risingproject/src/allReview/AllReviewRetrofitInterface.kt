package com.example.risingproject.src.allReview

import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AllReviewRetrofitInterface {
    @GET("/app/reviews/{userId}")
    fun getDetailInfo(@Path("userId") userId : Int,
                      @Query("itemId") itemId : Int
    ) : Call<Any>

}