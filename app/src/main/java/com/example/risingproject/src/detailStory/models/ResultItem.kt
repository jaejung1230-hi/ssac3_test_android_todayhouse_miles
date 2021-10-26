package com.example.risingproject.src.detailStory.models

import com.google.gson.annotations.SerializedName

data class ResultItem (
    @SerializedName("area") val area: String,
    @SerializedName("HousingType") val HousingType: String,
    @SerializedName("style") val style: String,
    @SerializedName("PlaceType") val PlaceType: String,
    @SerializedName("keywords") val keywords: List<keywords>,
    @SerializedName("photo") val photo: List<photo>,
    @SerializedName("context") val context:  List<keywords>,
    @SerializedName("pos") val pos: List<pos>
    )