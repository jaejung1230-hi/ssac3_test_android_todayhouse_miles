package com.example.risingproject.src.myReview

import com.example.risingproject.src.myReview.models.MyReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MyListRetrofitInterface {
    @GET("app/{userId}/reviews")
    fun getMyReview(@Path("userId") userId : Int) : Call<MyReviewResponse>

}