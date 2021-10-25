package com.example.risingproject.src.main.home.homemain

import com.example.risingproject.config.BaseResponse
import com.example.risingproject.src.main.home.homemain.models.getPicResponse
import com.example.risingproject.src.uploadpic.models.UploadPicRequest
import retrofit2.Call
import retrofit2.http.*


interface HomeMainRetrofitInterface {

    @GET("app/extras/stories")
    fun getPic() : Call<getPicResponse>
}