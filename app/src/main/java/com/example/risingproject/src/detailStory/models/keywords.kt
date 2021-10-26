package com.example.risingproject.src.detailStory.models

import com.google.gson.annotations.SerializedName

data class keywords (
    @SerializedName("storyId") val storyId: Int,
    @SerializedName("content") val content: String,
        )