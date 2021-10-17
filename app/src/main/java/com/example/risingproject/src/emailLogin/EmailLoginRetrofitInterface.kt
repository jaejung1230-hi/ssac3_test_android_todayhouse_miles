package com.example.risingproject.src.emailLogin

import com.example.risingproject.src.emailLogin.models.GetSignInRequest
import com.example.risingproject.src.emailLogin.models.GetSignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface EmailLoginRetrofitInterface {
    @POST("login")
    fun getSignIn(@Body params: GetSignInRequest): Call<GetSignInResponse>
}