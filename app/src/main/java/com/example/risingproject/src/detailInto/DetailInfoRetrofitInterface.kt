package com.example.risingproject.src.detailInto

import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DetailInfoRetrofitInterface {
    @GET("app/{itemId}")
    fun getDetailInfo(@Path("itemId") itemId : Int) : Call<GetDetailInfoResponse>

    @GET("app/")
    fun getCategoryItem(@Query("menuId") menuId : Int) : Call<GetCategoryItemResponse>
}