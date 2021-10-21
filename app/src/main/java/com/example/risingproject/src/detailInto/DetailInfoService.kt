package com.example.risingproject.src.detailInto

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
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
}