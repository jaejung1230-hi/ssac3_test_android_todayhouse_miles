package com.example.risingproject.src.detailInto

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityDetailInfoBinding
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse



class DetailInfoActivity : BaseActivity<ActivityDetailInfoBinding>(ActivityDetailInfoBinding::inflate), DetailInfoAcitivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getIntExtra("itemId",-1)

        DetailInfoService(this).tryGetItemSearch(intent.getIntExtra("itemId",-1))
        binding.tvDetailItemPriceBefore.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG

        binding.nastedStickyScrollView.run {
            header = binding.tabsDetail
            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }

    }

    override fun onGetDetailInfoSuccess(response: GetDetailInfoResponse) {
        Log.d("DetailInfo",response.result[0].photoImage.toString())
        Log.d("DetailInfo",response.result[1].itemInfo.toString())
        Log.d("DetailInfo",response.result[2].totalReivewRate.toString())
        Log.d("DetailInfo",response.result[3].totalReviewNum.toString())
        Log.d("DetailInfo",response.result[4].reviewList.toString())

    }

    override fun onGetDetailInfoFailure(message: String) {
        Log.d("DetailInfo",message)
    }
}