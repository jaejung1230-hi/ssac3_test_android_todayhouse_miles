package com.example.risingproject.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.risingproject.R
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentMyPageBinding
import com.example.risingproject.src.login.LoginActivity
import com.example.risingproject.src.myReview.MyReviewActivity


class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    private var mCount = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonChangeCounterText.setOnClickListener {
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString(ApplicationClass.X_ACCESS_TOKEN, null)
            editor.commit()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            context?.startActivity(intent)
            ActivityCompat.finishAffinity(requireActivity())
        }

        binding.linearMyReview.setOnClickListener {
            val intent = Intent(requireContext(), MyReviewActivity::class.java)
            context?.startActivity(intent)
        }
    }
}