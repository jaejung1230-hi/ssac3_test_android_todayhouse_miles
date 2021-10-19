package com.example.risingproject.src.main.store.storehome

import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemUseFilterResponse

interface StoreCategoryFragmentView {

    fun onGetCategoryItemSuccess(response: GetCategoryItemResponse)

    fun onGetCategoryItemFailure(message: String)

    fun onGetCategoryItemUseFilterSuccess(response: GetCategoryItemUseFilterResponse)

    fun onGetCategoryItemUseFilterFailure(message: String)
}