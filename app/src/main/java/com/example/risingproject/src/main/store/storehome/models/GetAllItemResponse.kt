package com.example.risingproject.src.main.store.storehome.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetAllItemResponse (
    @SerializedName("result") val result: List<ResultItemAll>
): BaseResponse()