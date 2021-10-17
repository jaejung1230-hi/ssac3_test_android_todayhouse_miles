package com.example.risingproject.src.emailLogin

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityLoginEmailBinding
import com.example.risingproject.src.emailLogin.models.GetSignInRequest
import com.example.risingproject.src.emailLogin.models.GetSignInResponse
import com.example.risingproject.src.main.MainActivity

class EmailLoginActivity : BaseActivity<ActivityLoginEmailBinding>(ActivityLoginEmailBinding::inflate),EmailLoginActivityView {

    var flag_email_yet = true
    var flag_pwd_yet = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarSigninEmail)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.title = ""

        binding.btnSignin.setOnClickListener {
            val getSignInRequest = GetSignInRequest(binding.editEmailLogin.text.toString(),
                    binding.editPasswordLogin.text.toString()
            )
            EmailLoginService(this).tryGetSignIn(getSignInRequest)
            showLoadingMessageDialog(this,"로그인 시도 중입니다")

        }
        binding.editEmailLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                flag_email_yet = !checkLength(s)
                checkBtnCilck()
            }
        })

        binding.editPasswordLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                flag_pwd_yet = !checkLength(s)
                checkBtnCilck()
            }
        })

        binding.btnSignin.isClickable = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun onGetSignInSuccess(response: GetSignInResponse) {
        dismissLoadingMessageDialog()
        if(!response.message.equals("성공")){
            showCustomToast("아이디나 비밀번호를 확인해주세요")
        }
        else{
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt)
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onGetSignInFailure(message: String) {
        showCustomToast("로그인에 실패했습니다.")
    }

    fun checkLength(s: CharSequence?): Boolean {
        return s?.length!! >= 1
    }

    fun checkBtnCilck(){
        if(!flag_email_yet && !flag_pwd_yet){
            binding.btnSignin.isClickable = true
            binding.btnSignin.setBackgroundColor(Color.parseColor("#7adbfe"))
        }else{

            binding.btnSignin.setBackgroundColor(Color.parseColor("#a3e4f8"))
            binding.btnSignin.isClickable = false
        }
    }


}