package com.example.risingproject.src.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityAllReviewBinding
import com.example.risingproject.databinding.ActivityCartBinding
import com.example.risingproject.src.allReview.models.AllReviewResponse
import com.example.risingproject.src.allReview.util.ReviewsAdapter
import com.example.risingproject.src.cart.model.CartResponse
import com.example.risingproject.src.cart.util.CartAdapter

class CartActivity : BaseActivity<ActivityCartBinding>(ActivityCartBinding::inflate),CartAcitivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        CartService(this).tryGetCart(ApplicationClass.sSharedPreferences.getInt(LOG_IN_USER,0))
    }

    override fun onGetCartSuccess(response: CartResponse) {
        Log.d("CartActivity",response.result.toString())
        binding.tvPriceItem.text = response.result[1].totalPriceInfo[0].totalPrice
        binding.tvPriceDele.text = response.result[1].totalPriceInfo[0].totalDeliveryFee
        binding.tvPriceDis.text = response.result[1].totalPriceInfo[0].totalSalePrice
        binding.tvPriceTotal.text = response.result[1].totalPriceInfo[0].price
        val totalPrice = response.result[1].totalPriceInfo[0].totalPrice.substring(0,response.result[1].totalPriceInfo[0].totalPrice.length-1)
        val totalDeliveryFee = response.result[1].totalPriceInfo[0].totalDeliveryFee.substring(0,response.result[1].totalPriceInfo[0].totalDeliveryFee.length-1)
        val totalSalePrice = response.result[1].totalPriceInfo[0].totalSalePrice.substring(0,response.result[1].totalPriceInfo[0].totalSalePrice.length-1)
        val price = response.result[1].totalPriceInfo[0].price.substring(0,response.result[1].totalPriceInfo[0].price.length-1)
        Log.d("CartActivity",totalPrice)
        Log.d("CartActivity",totalDeliveryFee)
        Log.d("CartActivity",totalSalePrice)
        Log.d("CartActivity",price)

        binding.recyclerCart.layoutManager = LinearLayoutManager(this)
        binding.recyclerCart.adapter = CartAdapter(this,response.result[0].Items)

    }

    override fun onGetCartFailure(message: String) {
        Log.d("CartActivity",message.toString())
    }

}