package com.example.risingproject.src.emailLogin

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.src.emailLogin.models.GetSignInRequest
import com.example.risingproject.src.emailLogin.models.GetSignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailLoginService(val view : EmailLoginActivityView) {

    fun tryGetSignIn(getSignInRequest : GetSignInRequest){
        val signupOneRetrofitInterface = ApplicationClass.sRetrofit.create(EmailLoginRetrofitInterface::class.java)

        signupOneRetrofitInterface.getSignIn(getSignInRequest).enqueue(object : Callback<GetSignInResponse> {
            override fun onResponse(call: Call<GetSignInResponse>, response: Response<GetSignInResponse>) {
                Log.d("test",response.body().toString())
                view.onGetSignInSuccess(response.body() as GetSignInResponse)
            }

            override fun onFailure(call: Call<GetSignInResponse>, t: Throwable) {
                view.onGetSignInFailure(t.message ?: "통신 오류")
            }
        })
    }
}