package com.example.risingproject.src.detailInto

import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse
import com.example.risingproject.src.search.storeSearch.models.GetSearchItemResponse

interface DetailInfoAcitivityView {

    fun onGetDetailInfoSuccess(response: GetDetailInfoResponse)

    fun onGetDetailInfoFailure(message: String)

}