package com.example.risingproject.src.detailInto.buy.models

import com.google.gson.annotations.SerializedName

data class postCartRequest (
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("firstOption") val firstOption: Int,
    @SerializedName("secondOption") val secondOption: Int,
    @SerializedName("num") val num: Int,
        )