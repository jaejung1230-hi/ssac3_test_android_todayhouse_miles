package com.example.risingproject.src.detailInto.buy

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse
import com.example.risingproject.src.search.storeSearch.models.GetSearchItemResponse

interface ByFragmentView {

    fun onGetbasketInfoSuccess(response: GetDetailInfoResponse)

    fun onGetbasketInfoFailure(message: String)

    fun onPostCartSuccess(response: BaseResponse)

    fun onPostCartFailure(message: String)
}