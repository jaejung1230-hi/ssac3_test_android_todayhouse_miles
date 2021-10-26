package com.example.risingproject.src.detailInto

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.detailInto.buy.BuyRetrofitInterface
import com.example.risingproject.src.detailInto.buy.ByFragmentView
import com.example.risingproject.src.detailInto.buy.models.postCartRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BuyFragmentService(val view : ByFragmentView){
    fun tryPostCart(userId: Int, postCartRequest : postCartRequest){
        val buyRetrofitInterface = ApplicationClass.sRetrofit.create(BuyRetrofitInterface::class.java)
        buyRetrofitInterface.postCart(userId, postCartRequest).enqueue(object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("test",response.toString())
                view.onPostCartSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                view.onPostCartFailure(t.message ?: "통신 오류")
            }

        })
    }
}