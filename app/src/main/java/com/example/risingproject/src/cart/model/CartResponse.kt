package com.example.risingproject.src.cart.model

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class CartResponse (
    @SerializedName("result") val result: List<result>
        ):BaseResponse()