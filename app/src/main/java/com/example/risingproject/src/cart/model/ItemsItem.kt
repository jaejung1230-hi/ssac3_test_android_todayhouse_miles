package com.example.risingproject.src.cart.model

import com.google.gson.annotations.SerializedName

data class ItemsItem(
    @SerializedName("ItemName") val ItemName: String,
    @SerializedName("deliveryFee") val deliveryFee: String,
    @SerializedName("deliveryFrom") val deliveryFrom: String,
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("num") val num: Int,
    @SerializedName("optionName") val optionName: String,
    @SerializedName("price") val price: String
)