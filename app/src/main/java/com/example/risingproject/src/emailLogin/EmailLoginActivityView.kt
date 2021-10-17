package com.example.risingproject.src.emailLogin

import com.example.risingproject.src.emailLogin.models.GetSignInResponse

interface EmailLoginActivityView {

    fun onGetSignInSuccess(response: GetSignInResponse)

    fun onGetSignInFailure(message: String)
}