package com.example.risingproject.src.myReview

import com.example.risingproject.src.myReview.models.MyReviewResponse

interface MyReviewAcitivityView {

    fun onGetMyReviewSuccess(response: MyReviewResponse)

    fun onGetMyReviewFailure(message: String)


}