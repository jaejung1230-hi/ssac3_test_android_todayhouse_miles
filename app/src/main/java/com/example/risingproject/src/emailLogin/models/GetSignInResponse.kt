package com.example.risingproject.src.emailLogin.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetSignInResponse(
    @SerializedName("result") val result: ResultSignUp
):BaseResponse()