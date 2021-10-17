package com.example.risingproject.src.signup

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.signup.models.GetSignInRequest
import com.example.risingproject.src.signup.models.GetSignInResponse
import com.example.risingproject.src.signup.models.PostSignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignupOneRetrofitInterface {
    @POST("users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<BaseResponse>

    @POST("login")
    fun getSignIn(@Body params: GetSignInRequest): Call<GetSignInResponse>
}