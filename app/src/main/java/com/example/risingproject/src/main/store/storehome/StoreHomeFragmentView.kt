package com.example.risingproject.src.main.store.storehome

import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse

interface StoreHomeFragmentView {

    fun onGetAllItemSuccess(response: GetAllItemResponse)

    fun onGetAllItemFailure(message: String)


}