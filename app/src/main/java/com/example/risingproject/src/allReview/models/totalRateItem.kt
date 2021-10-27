package com.example.risingproject.src.allReview.models

import com.google.gson.annotations.SerializedName

data class totalRateItem(
    @SerializedName("1points") val `1points`: Int,
    @SerializedName("2points") val `2points`: Int,
    @SerializedName("3points") val `3points`: Int,
    @SerializedName("4points") val `4points`: Int,
    @SerializedName("5points") val `5points`: Int,
    @SerializedName("avgOfRate") val avgOfRate: Float,
    @SerializedName("reviewAvg") val reviewAvg: Int
)