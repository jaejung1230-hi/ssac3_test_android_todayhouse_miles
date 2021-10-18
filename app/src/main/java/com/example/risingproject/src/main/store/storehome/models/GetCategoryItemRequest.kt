package com.example.risingproject.src.main.store.storehome.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetCategoryItemRequest (
    @SerializedName("menuId") val menuId: Int
)