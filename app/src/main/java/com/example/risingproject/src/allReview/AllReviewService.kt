package com.example.risingproject.src.allReview

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.allReview.models.AllReviewResponse
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.StoreCategoryRetrofitInterface
import com.example.risingproject.src.main.store.storehome.StoreHomeRetrofitInterface
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemRequest
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllReviewService(val view : AllReviewAcitivityView){
    fun tryGetAllReview(userId: Int, itemId: Int){
        val detailInfoRetrofitInterface = ApplicationClass.sRetrofit.create(AllReviewRetrofitInterface::class.java)
        detailInfoRetrofitInterface.getDetailInfo(userId, itemId).enqueue(object : Callback<AllReviewResponse>{
            override fun onResponse(call: Call<AllReviewResponse>, response: Response<AllReviewResponse>) {
                Log.d("AllReviewActivity",response.toString())
                view.onGetAllReviewSuccess(response.body() as AllReviewResponse)
            }

            override fun onFailure(call: Call<AllReviewResponse>, t: Throwable) {
                view.onGetAllReviewFailure(t.message ?: "통신 오류")
            }

        })
    }

}