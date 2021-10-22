package com.example.risingproject.src.main.store.storehome

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.main.store.storehome.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoreHomeService(val view : StoreHomeFragemt) {
    fun tryGetCategoryItem(){
        val storeCategoryRetrofitInterface = ApplicationClass.sRetrofit.create(StoreHomeRetrofitInterface::class.java)
        storeCategoryRetrofitInterface.getCategoryItem().enqueue(object : Callback<GetAllItemResponse> {
            override fun onResponse(call: Call<GetAllItemResponse>, response: Response<GetAllItemResponse>) {
                Log.d("test1",response.toString())
                view.onGetAllItemSuccess(response.body() as GetAllItemResponse)
            }

            override fun onFailure(call: Call<GetAllItemResponse>, t: Throwable) {
                view.onGetAllItemFailure(t.message ?: "통신 오류")
            }
        })
    }
}