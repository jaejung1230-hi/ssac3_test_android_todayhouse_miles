package com.example.risingproject.src.uploadpic

import com.example.risingproject.config.BaseResponse

interface UploadPicActivityView {
    fun onPostPicSuccess(response: BaseResponse)

    fun onPostPicFailure(message: String)
}