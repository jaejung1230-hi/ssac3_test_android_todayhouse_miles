package com.example.risingproject.src.uploadpic.models

import com.google.gson.annotations.SerializedName

data class UploadPicRequest (
    @SerializedName("context") val context: String,
    @SerializedName("photos") val photos: String,
    @SerializedName("area") val area: Int,
    @SerializedName("housingTye") val housingTye: Int,
    @SerializedName("style") val style: Int,
    @SerializedName("placeType") val placeType: Int,
    @SerializedName("keywords") val keywords: List<String>,
    @SerializedName("posXs") val posXs: List<Float>,
    @SerializedName("posYs") val posYs: List<Float>,
    @SerializedName("itemIds") val itemIds: List<Int>,
        )