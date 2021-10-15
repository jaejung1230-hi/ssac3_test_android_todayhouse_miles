package com.example.risingproject.src.main.home

import com.example.risingproject.src.main.home.models.SignUpResponse
import com.example.risingproject.src.main.home.models.UserResponse

interface HomeFragmentView {

    fun onGetUserSuccess(response: UserResponse)

    fun onGetUserFailure(message: String)

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}