package com.example.risingproject.src.main.store.storehome.models

import com.google.gson.annotations.SerializedName

data class ResultItemAll (
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("sale") val sale: Int,
    @SerializedName("percenttage") val percenttage: Int,
    @SerializedName("reviewRate") val reviewRate: Double,
    @SerializedName("numOfReviews") val numOfReviews: Int?,
    @SerializedName("mainPhoto") val mainPic: String?,
    @SerializedName("salePrice") val salePrice: Int,
    )