package com.example.risingproject.src.main.store.storehome

import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreCategoryRetrofitInterface {
    @GET("app/")
    fun getCategoryItem(@Query("menuId") menuId : Int) : Call<GetCategoryItemResponse>

    @GET("app/{menuId}/items")
    fun getCategoryItemUseFilter(
        @Path("menuId") menuId : Int,
        @Query("numOfPeople") numOfPeople: Int?,
        @Query("material") material: String?,
        @Query("used") used: Int?,
        @Query("color") color: String?,
        @Query("season") season: String?,
        @Query("pattern") pattern: String?,
        @Query("brand") brand: String?,
        @Query("energyEfficiency") energyEfficiency: Int?,
        @Query("type") type: String?,
        @Query("design") design: String?,
        @Query("page") page: Int,
        @Query("num") num: Int
    ) : Call<GetCategoryItemUseFilterResponse>
}