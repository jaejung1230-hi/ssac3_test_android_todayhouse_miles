package com.example.risingproject.src.main.store.storehome

import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import retrofit2.Call
import retrofit2.http.GET

interface StoreHomeRetrofitInterface {
    @GET("app/items")
    fun getCategoryItem() : Call<GetAllItemResponse>

}