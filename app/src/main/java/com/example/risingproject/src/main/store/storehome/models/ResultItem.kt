package com.example.risingproject.src.main.store.storehome.models

import com.google.gson.annotations.SerializedName

data class ResultItem (
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("sale") val sale: Int,
    @SerializedName("percenttage") val percenttage: Int,
    @SerializedName("deliveryFee") val deliveryFee: String,
    @SerializedName("numOfReviews") val numOfReviews: Int,
    @SerializedName("reviewRate") val reviewRate: Int
    )