package com.example.risingproject.src.uploadpic

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.uploadpic.models.UploadPicRequest
import retrofit2.Call
import retrofit2.http.*


interface UploadPicRetrofitInterface {

    @POST("app/userId/{userId}/stories")
    fun postReview(@Path("userId") userId : Int,  @Body params: UploadPicRequest) : Call<BaseResponse>
}