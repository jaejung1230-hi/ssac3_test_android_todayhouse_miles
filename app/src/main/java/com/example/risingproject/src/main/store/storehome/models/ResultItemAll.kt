package com.example.risingproject.src.main.store.storehome.models

import com.google.gson.annotations.SerializedName

data class ResultItemAll (
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("price") val sale: Int,
    @SerializedName("percenttage") val percenttage: Int,
    @SerializedName("reviewRate") val reviewRate: Double,
    @SerializedName("numOfreview") val numOfReviews: Int?,
    @SerializedName("mainPhoto") val mainPic: String?,
    @SerializedName("sale") val salePrice: Int,
    )