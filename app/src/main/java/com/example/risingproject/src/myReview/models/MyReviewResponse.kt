package com.example.risingproject.src.myReview.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class MyReviewResponse(
    @SerializedName("result") val result: List<ResultReview>
):BaseResponse()