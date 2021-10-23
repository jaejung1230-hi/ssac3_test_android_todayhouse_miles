package com.example.risingproject.src.main.store.storehome

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemRequest
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterRequest
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoreCategoryService(val view : StoreCategoryFragmentView) {
    fun tryGetCategoryItem(getCategoryItemRequest : GetCategoryItemRequest){
        val storeCategoryRetrofitInterface = ApplicationClass.sRetrofit.create(StoreCategoryRetrofitInterface::class.java)
        storeCategoryRetrofitInterface.getCategoryItem(getCategoryItemRequest.menuId).enqueue(object : Callback<GetCategoryItemResponse> {
            override fun onResponse(call: Call<GetCategoryItemResponse>, response: Response<GetCategoryItemResponse>) {
                Log.d("test",response.toString())
                view.onGetCategoryItemSuccess(response.body() as GetCategoryItemResponse)
            }

            override fun onFailure(call: Call<GetCategoryItemResponse>, t: Throwable) {
                view.onGetCategoryItemFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryGetCategoryItemUseFilter(getCategoryItemUseFilterRequest : GetCategoryItemUseFilterRequest){
        val storeCategoryRetrofitInterface = ApplicationClass.sRetrofit.create(StoreCategoryRetrofitInterface::class.java)
        storeCategoryRetrofitInterface.getCategoryItemUseFilter(getCategoryItemUseFilterRequest.menuId,getCategoryItemUseFilterRequest.numOfPeople, getCategoryItemUseFilterRequest.material, getCategoryItemUseFilterRequest.used, getCategoryItemUseFilterRequest.color, getCategoryItemUseFilterRequest.season,
            getCategoryItemUseFilterRequest.pattern,getCategoryItemUseFilterRequest.brand,getCategoryItemUseFilterRequest.energyEfficiency,getCategoryItemUseFilterRequest.type,
            getCategoryItemUseFilterRequest.design).enqueue(object : Callback<GetCategoryItemUseFilterResponse> {
            override fun onResponse(call: Call<GetCategoryItemUseFilterResponse>, response: Response<GetCategoryItemUseFilterResponse>) {
                Log.d("test2",response.toString())
                Log.d("test2",response.message().toString())
                Log.d("test2",response.raw().toString())
                view.onGetCategoryItemUseFilterSuccess(response.body() as GetCategoryItemUseFilterResponse)
            }

            override fun onFailure(call: Call<GetCategoryItemUseFilterResponse>, t: Throwable) {
                Log.d("test2",t.toString())
                Log.d("test2",t.message.toString())
                view.onGetCategoryItemUseFilterFailure(t.message ?: "통신 오류")
            }

        })
    }
}