package com.example.risingproject.src.main.home.homemain.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class getPicResponse(
    @SerializedName("result") val result: List<ResultPic>
):BaseResponse()