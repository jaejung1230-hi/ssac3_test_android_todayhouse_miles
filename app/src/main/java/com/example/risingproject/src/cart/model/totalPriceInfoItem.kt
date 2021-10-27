package com.example.risingproject.src.cart.model

import com.google.gson.annotations.SerializedName

data class totalPriceInfoItem(
    @SerializedName("price") val price: String,
    @SerializedName("totalDeliveryFee") val totalDeliveryFee: String,
    @SerializedName("totalPrice") val totalPrice: String,
    @SerializedName("totalSalePrice") val totalSalePrice: String
)