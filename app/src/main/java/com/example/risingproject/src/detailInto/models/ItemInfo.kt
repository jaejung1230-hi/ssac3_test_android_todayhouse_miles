package com.example.risingproject.src.detailInto.models

import com.google.gson.annotations.SerializedName

data class ItemInfo(
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("reviewRate") val reviewRate: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("sale") val sale: Int,
    @SerializedName("percenttage") val percenttage: Int,
    @SerializedName("profit") val profit: String,
    @SerializedName("monthlyPay") val monthlyPay: String,
    @SerializedName("deliveryFee") val deliveryFee: String?,
    @SerializedName("paymentMethod") val paymentMethod: String?,
    @SerializedName("freeDeliveryFeeCondition") val freeDeliveryFeeCondition: String,
    @SerializedName("deliveryWay") val deliveryWay: String,
    @SerializedName("impossiblePlace") val impossiblePlace: String?,
    @SerializedName("extraDeliveryFee") val extraDeliveryFee: String?,
    @SerializedName("detailedImage") val detailedImage: String?,
)