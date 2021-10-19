package com.example.risingproject.src.main.store.storehome

import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreCategoryRetrofitInterface {
    @GET("items")
    fun getCategoryItem(@Query("menuId") menuId : Int) : Call<GetCategoryItemResponse>
    @GET("{menuId}/items")
    fun getCategoryItemUseFilter(@Path("menuId") menuId : Int) : Call<GetCategoryItemResponse>
}