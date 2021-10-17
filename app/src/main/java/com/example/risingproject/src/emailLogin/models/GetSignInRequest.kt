package com.example.risingproject.src.emailLogin.models

import com.google.gson.annotations.SerializedName

data class GetSignInRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)