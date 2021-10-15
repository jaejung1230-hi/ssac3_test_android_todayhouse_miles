package com.example.risingproject.src.main.home

import com.example.risingproject.src.main.home.models.PostSignUpRequest
import com.example.risingproject.src.main.home.models.SignUpResponse
import com.example.risingproject.src.main.home.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeRetrofitInterface {
    @GET("/users")
    fun getUsers() : Call<UserResponse>

    @POST("/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>
}