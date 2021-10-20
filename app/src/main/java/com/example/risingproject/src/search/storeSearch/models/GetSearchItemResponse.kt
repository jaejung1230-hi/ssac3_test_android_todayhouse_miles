package com.example.risingproject.src.search.storeSearch.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetSearchItemResponse (
    @SerializedName("result") val result: List<ResultItem>
): BaseResponse()