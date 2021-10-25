package com.example.risingproject.src.detailStory

import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse
import com.example.risingproject.src.search.storeSearch.models.GetSearchItemResponse

interface DetailStoryAcitivityView {

    fun onGetDetailStorySuccess(response: Any)

    fun onGetDetailStoryFailure(message: String)


}