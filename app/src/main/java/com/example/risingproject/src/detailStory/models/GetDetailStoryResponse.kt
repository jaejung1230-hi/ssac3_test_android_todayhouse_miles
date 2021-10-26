package com.example.risingproject.src.detailStory.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetDetailStoryResponse (
    @SerializedName("result") val result: List<ResultItem>
): BaseResponse()