package com.example.risingproject.src.detailInto

import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface DetailInfoRetrofitInterface {
    @GET("app/{itemId}")
    fun getDetailInfo(@Path("itemId") itemId : Int) : Call<GetDetailInfoResponse>
}