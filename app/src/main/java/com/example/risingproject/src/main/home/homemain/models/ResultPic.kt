package com.example.risingproject.src.main.home.homemain.models

import com.google.gson.annotations.SerializedName

data class ResultPic (
    @SerializedName("storyId") val storyId: Int,
    @SerializedName("photo") val photo: String,
    @SerializedName("userNickName") val userNickName: String,
        )