package com.example.risingproject.src.allReview.models

import com.google.gson.annotations.SerializedName

data class ReviewsItem(
    @SerializedName("context") val context: String,
    @SerializedName("helpedNum") val helpedNum: Int,
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("photo") val photo: String,
    @SerializedName("reviewAvg") val reviewAvg: Float,
    @SerializedName("reviewId") val reviewId: Int,
    @SerializedName("uploadTime") val uploadTime: String,
    @SerializedName("userNickName") val userNickName: String
)