package com.example.risingproject.src.allReview.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class AllReviewResponse (
    @SerializedName("result") val result: List<ResultAll>
): BaseResponse()