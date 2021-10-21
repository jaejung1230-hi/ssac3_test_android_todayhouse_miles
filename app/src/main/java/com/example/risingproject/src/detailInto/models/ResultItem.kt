package com.example.risingproject.src.detailInto.models

import com.google.gson.annotations.SerializedName

data class ResultItem (
    @SerializedName("photoImage") val photoImage: List<PhotoImage>,
    @SerializedName("itemInfo") val itemInfo: List<ItemInfo>,
    @SerializedName("totalReivewRate") val totalReivewRate: List<TotalReivewRate>,
    @SerializedName("totalReviewNum") val totalReviewNum: List<TotalReviewNum>,
    @SerializedName("reviewList") val reviewList: List<Any>,
    )