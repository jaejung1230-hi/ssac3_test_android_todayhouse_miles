package com.example.risingproject.src.allReview.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class result (
    @SerializedName("totalRate") val totalRate : List<totalRateItem>,
    @SerializedName("photoReviews") val photoReviews : List<photoReviewsItem>,
    @SerializedName("Reviews") val Reviews : List<ReviewsItem>,
        )
