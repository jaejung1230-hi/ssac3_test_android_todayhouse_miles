package com.example.risingproject.src.main.store.storehome

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoreCategoryService(val view : StoreCategoryFragmentView) {
    fun tryGetCategoryItem(){
        val storeCategoryRetrofitInterface = ApplicationClass.sRetrofit.create(StoreCategoryRetrofitInterface::class.java)
        storeCategoryRetrofitInterface.getCategoryItem(2).enqueue(object : Callback<GetCategoryItemResponse> {
            override fun onResponse(call: Call<GetCategoryItemResponse>, response: Response<GetCategoryItemResponse>) {
                Log.d("test",response.body()!!.message.toString())
                view.onGetCategoryItemSuccess(response.body() as GetCategoryItemResponse)
            }

            override fun onFailure(call: Call<GetCategoryItemResponse>, t: Throwable) {
                view.onGetCategoryItemFailure(t.message ?: "통신 오류")
            }

        })

    }
}