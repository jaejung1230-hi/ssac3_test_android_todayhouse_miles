package com.example.risingproject.src.main.store.storehome.models

import com.google.gson.annotations.SerializedName

data class ResultItem (
    @SerializedName("companyName") val companyName: String,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("sale") val sale: String,
    @SerializedName("percenttage") val percenttage: String,
    @SerializedName("deliveryFee") val deliveryFee: String,
    @SerializedName("numOfReviews") val numOfReviews: String,
    @SerializedName("reviewRate") val reviewRate: String
    )