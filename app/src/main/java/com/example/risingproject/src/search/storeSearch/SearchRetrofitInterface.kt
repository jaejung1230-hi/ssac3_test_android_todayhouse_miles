package com.example.risingproject.src.search.storeSearch

import com.example.risingproject.src.search.storeSearch.models.GetSearchItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRetrofitInterface {
    @GET("app/shops")
    fun getItemSearch(@Query("itemName") itemName : String) : Call<GetSearchItemResponse>
}