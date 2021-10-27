package com.example.risingproject.src.allReview.models

import com.google.gson.annotations.SerializedName

data class photoReviewsItem(
    @SerializedName("photo") val photo: String,
    @SerializedName("reviewId") val reviewId: Int
)