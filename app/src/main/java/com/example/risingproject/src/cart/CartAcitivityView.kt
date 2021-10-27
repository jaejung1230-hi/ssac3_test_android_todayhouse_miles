package com.example.risingproject.src.cart

import com.example.risingproject.src.cart.model.CartResponse

interface CartAcitivityView {

    fun onGetCartSuccess(response: CartResponse)

    fun onGetCartFailure(message: String)
}