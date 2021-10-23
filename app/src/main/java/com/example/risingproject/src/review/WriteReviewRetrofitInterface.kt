package com.example.risingproject.src.review

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.review.models.WriteReviewRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface WriteReviewRetrofitInterface {

    @POST("app/{userId}/reviews")
    fun postReview(@Path("userId") userId : Int,  @Body params: WriteReviewRequest) : Call<BaseResponse>
}