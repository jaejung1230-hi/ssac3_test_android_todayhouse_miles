package com.example.risingproject.src.search.storeSearch

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse
import com.example.risingproject.src.search.storeSearch.models.GetSearchItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchService(val view :SearchFragmentView){
    fun tryGetItemSearch(itemName : String){
        val searchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        searchRetrofitInterface.getItemSearch(itemName).enqueue(object : Callback<GetSearchItemResponse>{
            override fun onResponse(call: Call<GetSearchItemResponse>, response: Response<GetSearchItemResponse>) {
                Log.d("test",response.toString())
                view.onGetItemSearchSuccess(response.body() as GetSearchItemResponse)
            }

            override fun onFailure(call: Call<GetSearchItemResponse>, t: Throwable) {
                view.onGetItemSearchFailure(t.message ?: "통신 오류")
            }

        })
    }
}