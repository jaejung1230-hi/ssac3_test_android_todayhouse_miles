package com.example.risingproject.src.detailStory

import com.example.risingproject.src.detailStory.models.GetDetailStoryResponse

interface DetailStoryAcitivityView {

    fun onGetDetailStorySuccess(response: GetDetailStoryResponse)

    fun onGetDetailStoryFailure(message: String)


}