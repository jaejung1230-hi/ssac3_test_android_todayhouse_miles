package com.example.risingproject.src.signup

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.signup.models.GetSignInResponse

interface SignupOneActivityView {

    fun onPostSignUpSuccess(response: BaseResponse)

    fun onPostSignUpFailure(message: String)

    fun onGetSignInSuccess(response: GetSignInResponse)

    fun onGetSignInFailure(message: String)
}