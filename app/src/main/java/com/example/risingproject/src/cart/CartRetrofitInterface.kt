package com.example.risingproject.src.cart

import com.example.risingproject.src.allReview.models.AllReviewResponse
import com.example.risingproject.src.cart.model.CartResponse
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CartRetrofitInterface {
    @GET("/app/{userId}/carts")
    fun getCart(@Path("userId") userId : Int
    ) : Call<CartResponse>

}