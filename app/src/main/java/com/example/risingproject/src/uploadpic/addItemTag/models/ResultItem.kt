package com.example.risingproject.src.uploadpic.addItemTag.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultItem (
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("price") val price: Int,
    @SerializedName("sale") val sale: Int,
    @SerializedName("percenttage") val percenttage: Int,
    @SerializedName("reviewRate") val reviewRate: Int,
    @SerializedName("numOfReviews") val numOfReviews: Int,
    @SerializedName("mainPhoto") val mainPhoto: String

    ) : Parcelable