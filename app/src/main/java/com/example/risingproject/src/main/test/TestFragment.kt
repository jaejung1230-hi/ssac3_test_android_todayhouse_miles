package com.example.risingproject.src.main.test

import android.os.Bundle
import android.view.View
import com.example.risingproject.R
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentTestBinding

class TestFragment : BaseFragment<FragmentTestBinding>(FragmentTestBinding::bind, R.layout.fragment_test) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCustomToast("플레그먼트 환엽합니다.")
    }

}