package com.example.risingproject.src.signup

import android.util.Log
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.signup.models.GetSignInRequest
import com.example.risingproject.src.signup.models.GetSignInResponse
import com.example.risingproject.src.signup.models.PostSignUpRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupOneService(val view : SignupOneActivityView) {
    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val signupOneRetrofitInterface = ApplicationClass.sRetrofit.create(SignupOneRetrofitInterface::class.java)
        signupOneRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object : Callback<BaseResponse> {

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("test",response.body().toString())
                view.onPostSignUpSuccess(response.body() as BaseResponse)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetSignIn(getSignInRequest : GetSignInRequest){
        val signupOneRetrofitInterface = ApplicationClass.sRetrofit.create(SignupOneRetrofitInterface::class.java)

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