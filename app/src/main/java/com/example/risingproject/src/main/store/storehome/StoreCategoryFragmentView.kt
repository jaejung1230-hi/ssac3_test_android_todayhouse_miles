package com.example.risingproject.src.main.store.storehome

import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse

interface StoreCategoryFragmentView {

    fun onGetCategoryItemSuccess(response: GetCategoryItemResponse)

    fun onGetCategoryItemFailure(message: String)
}