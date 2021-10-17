package com.example.risingproject.src.login

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityLoginBinding
import com.example.risingproject.src.emailLogin.EmailLoginActivity
import com.example.risingproject.src.main.MainActivity
import com.example.risingproject.src.recommendinfo.CustomRecommendInfoActivity
import com.example.risingproject.src.signup.SignupOneActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvSigninEmail.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.tvSignupEmail.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.tvSignupEmail.setOnClickListener {
            val intent  = Intent(this, SignupOneActivity::class.java)
            startActivity(intent)
        }

        binding.tvSigninEmail.setOnClickListener {
            val intent  = Intent(this, EmailLoginActivity::class.java)
            startActivity(intent)
        }
    }
}