package com.example.risingproject.src.review.models

import com.google.gson.annotations.SerializedName

data class WriteReviewRequest (
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("photo") val photo: String,
    @SerializedName("content") val content: String,
    @SerializedName("durability") val durability: Int,
    @SerializedName("design") val design: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("delivery") val delivery: Int,
        )