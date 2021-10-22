package com.example.risingproject.src.detailInto

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.StoreCategoryRetrofitInterface
import com.example.risingproject.src.main.store.storehome.StoreHomeRetrofitInterface
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemRequest
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailInfoService(val view :DetailInfoAcitivityView){
    fun tryGetItemSearch(itemId: Int){
        val detailInfoRetrofitInterface = ApplicationClass.sRetrofit.create(DetailInfoRetrofitInterface::class.java)
        detailInfoRetrofitInterface.getDetailInfo(itemId).enqueue(object : Callback<GetDetailInfoResponse>{
            override fun onResponse(call: Call<GetDetailInfoResponse>, response: Response<GetDetailInfoResponse>) {
                Log.d("test",response.toString())
                view.onGetDetailInfoSuccess(response.body() as GetDetailInfoResponse)
            }

            override fun onFailure(call: Call<GetDetailInfoResponse>, t: Throwable) {
                view.onGetDetailInfoFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryGetCategoryItem(getCategoryItemRequest : GetCategoryItemRequest){
        val storeCategoryRetrofitInterface = ApplicationClass.sRetrofit.create(DetailInfoRetrofitInterface::class.java)
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
}