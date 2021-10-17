package com.example.risingproject.src.main.store.storehome.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetCategoryItemResponse (
    @SerializedName("result") val result: List<ResultItem>
): BaseResponse()