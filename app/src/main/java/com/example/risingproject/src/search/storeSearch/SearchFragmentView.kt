package com.example.risingproject.src.search.storeSearch

import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse
import com.example.risingproject.src.search.storeSearch.models.GetSearchItemResponse

interface SearchFragmentView {

    fun onGetItemSearchSuccess(response: GetSearchItemResponse)

    fun onGetItemSearchFailure(message: String)

}