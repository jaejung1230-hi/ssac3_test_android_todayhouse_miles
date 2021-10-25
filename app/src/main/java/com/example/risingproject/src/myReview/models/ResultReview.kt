package com.example.risingproject.src.myReview.models

import com.google.gson.annotations.SerializedName

data class ResultReview (
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("reviewId") val reviewId: Int,
    @SerializedName("context") val context: String,
    @SerializedName("reviewAvg") val reviewAvg: Float,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("boughtPrice") val boughtPrice: Int,
    @SerializedName("num") val num: Int,
    @SerializedName("confirmed") val confirmed: Int,
    @SerializedName("photoUrl") val photoUrl: String,
        )