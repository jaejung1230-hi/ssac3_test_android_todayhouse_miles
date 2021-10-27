package com.example.risingproject.src.cart

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.allReview.models.AllReviewResponse
import com.example.risingproject.src.cart.model.CartResponse
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.StoreCategoryRetrofitInterface
import com.example.risingproject.src.main.store.storehome.StoreHomeRetrofitInterface
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemRequest
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartService(val view : CartAcitivityView){
    fun tryGetCart(userId: Int){
        val cartRetrofitInterface = ApplicationClass.sRetrofit.create(CartRetrofitInterface::class.java)
        cartRetrofitInterface.getCart(userId).enqueue(object : Callback<CartResponse>{
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                Log.d("CartActivity",response.toString())
                view.onGetCartSuccess(response.body() as CartResponse)
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                view.onGetCartFailure(t.message ?: "통신 오류")
            }

        })
    }

}