package com.example.risingproject.src.review

import android.os.Bundle
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityWriteReviewBinding
import com.example.risingproject.src.detailInto.models.ItemInfo

class WirteReviewActivity : BaseActivity<ActivityWriteReviewBinding>(ActivityWriteReviewBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedItem = intent?.getParcelableExtra<ItemInfo?>("selectedItem")
        binding.tvSeletedItemName.text = selectedItem?.itemName
    }
}