package com.example.risingproject.src.detailStory.models

import com.google.gson.annotations.SerializedName

data class pos (
    @SerializedName("posXs") val posXs: Float,
    @SerializedName("posYs") val posYs:  Float,
    @SerializedName("itemIds") val itemIds: String,
    @SerializedName("photoURL") val photoUrl: String,
        )