package com.example.risingproject.src.search.storeSearch.models

import com.google.gson.annotations.SerializedName

data class ResultItem (
    @SerializedName("itemName") val itemName: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("price") val price: Int,
    @SerializedName("sale") val sale: Int,
    @SerializedName("percenttage") val percenttage: Int,
    @SerializedName("reviewRate") val reviewRate: Int,
    @SerializedName("numOfReviews") val numOfReviews: Int,
    @SerializedName("mainPhoto") val mainPhoto: String

    )