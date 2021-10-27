package com.example.risingproject.src.cart.model

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class result (
    @SerializedName("Items") val Items : List<ItemsItem>,
    @SerializedName("totalPriceInfo") val totalPriceInfo : List<totalPriceInfoItem>,
        )
