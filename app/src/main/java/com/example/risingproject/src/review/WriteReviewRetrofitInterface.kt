package com.example.risingproject.src.review

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.review.models.WriteReviewRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface WriteReviewRetrofitInterface {
    @Multipart
    @POST("app/{userId}/reviews")
    fun postReview(@Path("userId") userId : Int, @Part image : MultipartBody.Part, @Body params: WriteReviewRequest) : Call<BaseResponse>
}