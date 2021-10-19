package com.example.risingproject.src.main.store.storehome.models

import com.google.gson.annotations.SerializedName

data class ResultItemUseFilter (
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("mainPic") val mainPic: String?,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("salePrice") val salePrice: Int,
    @SerializedName("originalPrice") val originalPrice: Int,
    @SerializedName("percenttage") val percenttage: Int,
    @SerializedName("deliveryFee") val deliveryFee: String,
    @SerializedName("numOfReviews") val numOfReviews: Int?,
    @SerializedName("reviewRate") val reviewRate: Double
    )