package com.example.risingproject.src.detailInto.models

import com.google.gson.annotations.SerializedName

data class reviewList(
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("reviewId") val reviewId: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("reviewAvg") val reviewAvg: Double,
    @SerializedName("uploadTime") val concat: String,
    @SerializedName("photo") val photo: String?,
    @SerializedName("context") val context: String,
    @SerializedName("helpedNum") val helpedNum: Double,
)