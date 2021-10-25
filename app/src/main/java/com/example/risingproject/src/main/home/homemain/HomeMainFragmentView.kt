package com.example.risingproject.src.main.home.homemain

import com.example.risingproject.src.main.home.homemain.models.getPicResponse

interface HomeMainFragmentView {

    fun onGetPicsSuccess(response: getPicResponse)

    fun onGetPicsFailure(message: String)

}