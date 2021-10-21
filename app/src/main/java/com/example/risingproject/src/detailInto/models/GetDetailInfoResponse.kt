package com.example.risingproject.src.detailInto.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetDetailInfoResponse (
    @SerializedName("result") val result: List<ResultItem>
): BaseResponse()