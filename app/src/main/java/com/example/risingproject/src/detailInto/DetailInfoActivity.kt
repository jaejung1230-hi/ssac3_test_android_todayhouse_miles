package com.example.risingproject.src.detailInto

import android.os.Bundle
import android.util.Log
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityDetailInfoBinding
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse

class DetailInfoActivity : BaseActivity<ActivityDetailInfoBinding>(ActivityDetailInfoBinding::inflate), DetailInfoAcitivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getIntExtra("itemId",-1)

        DetailInfoService(this).tryGetItemSearch(intent.getIntExtra("itemId",-1))
    }

    override fun onGetDetailInfoSuccess(response: GetDetailInfoResponse) {
        Log.d("DetailInfo",response.toString())
    }

    override fun onGetDetailInfoFailure(message: String) {
        Log.d("DetailInfo",message)
    }
}