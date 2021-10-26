package com.example.risingproject.src.detailStory

import com.example.risingproject.src.detailStory.models.GetDetailStoryResponse
import com.example.risingproject.src.main.home.homemain.models.getPicResponse
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse

interface DetailStoryAcitivityView {

    fun onGetDetailStorySuccess(response: GetDetailStoryResponse)

    fun onGetDetailStoryFailure(message: String)

    fun onGetPicsSuccess(response: getPicResponse)

    fun onGetPicsFailure(message: String)

}