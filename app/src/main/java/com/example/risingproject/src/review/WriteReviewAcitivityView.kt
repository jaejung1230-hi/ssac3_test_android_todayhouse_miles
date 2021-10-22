package com.example.risingproject.src.review

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse
import com.example.risingproject.src.search.storeSearch.models.GetSearchItemResponse

interface WriteReviewActivityView {

    fun onPostWriteReviewSuccess(response: BaseResponse)

    fun onPostWriteReviewFailure(message: String)

}