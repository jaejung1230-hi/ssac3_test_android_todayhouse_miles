package com.example.risingproject.src.detailInto.buy

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.detailInto.buy.models.postCartRequest
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.signup.models.GetSignInRequest
import retrofit2.Call
import retrofit2.http.*


interface BuyRetrofitInterface {
    @GET("app/{userId}/purchases")
    fun getDetailInfo(@Path("userId") userId : Int,
                      @Query("itemId") itemId : Int,
                      @Query("firstOption") firstOption : Int,
    ) : Call<GetDetailInfoResponse>

    @POST("app/{userId}/carts")
    fun postCart(@Path("userId") userId : Int,
                 @Body params: postCartRequest
    ) : Call<BaseResponse>
}