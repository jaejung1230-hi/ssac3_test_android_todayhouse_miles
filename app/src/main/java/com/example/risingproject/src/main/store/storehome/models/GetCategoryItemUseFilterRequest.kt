package com.example.risingproject.src.main.store.storehome.models

import com.example.risingproject.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetCategoryItemUseFilterRequest (
    @SerializedName("menuId") val menuId: Int,
    @SerializedName("numOfPeople") val numOfPeople: Int?,
    @SerializedName("material") val material: String?,
    @SerializedName("used") val used: Int?,
    @SerializedName("color") val color: String?,
    @SerializedName("season") val season: String?,
    @SerializedName("pattern") val pattern: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("energyEfficiency") val energyEfficiency: Int?,
    @SerializedName("type") val type: String?,
    @SerializedName("design") val design: String?,
    @SerializedName("page") val page: Int?,
    @SerializedName("num") val num: Int?,
)