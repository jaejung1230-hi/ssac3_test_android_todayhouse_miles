package com.example.risingproject.src.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import com.example.risingproject.R
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.config.BaseResponse
import com.example.risingproject.databinding.ActivitySignupOneBinding
import com.example.risingproject.src.signup.models.GetSignInRequest
import com.example.risingproject.src.signup.models.GetSignInResponse
import com.example.risingproject.src.signup.models.PostSignUpRequest
import java.util.regex.Pattern
import android.content.SharedPreferences
import android.util.Log
import com.example.risingproject.src.main.MainActivity


class SignupOneActivity : BaseActivity<ActivitySignupOneBinding>(ActivitySignupOneBinding::inflate), SignupOneActivityView {
    val module_exports = "/^([\\w_\\.\\-\\+])+\\@([\\w\\-]+\\.)+([\\w]{2,10})+\$/;"
    val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    var pwdValidation = "^(?=.*[A-Za-z])(?=.*[0-9]).{8,15}\$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarSignup)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.title = ""

        binding.tvSigninTemp2.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.tvSigninTemp3.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.editEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmail()
            }
        })

        binding.editPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPassword()
            }
        })

        binding.editPasswordCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPasswordEquals()
            }
        })

        binding.editNickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkNickName()
            }
        })
        binding.btnNicknameUse.setOnClickListener {
            binding.editNickname.setText(binding.tvRecommendNickname.text)
        }

        var checkboxAllFlag = true

        binding.checkboxAll.setOnCheckedChangeListener { buttonView, isChecked ->
            if(checkboxAllFlag){
                if(isChecked){
                    binding.checkboxAge.isChecked = true
                    binding.checkboxAlarm.isChecked = true
                    binding.checkboxProfile.isChecked = true
                    binding.checkboxPromise.isChecked = true
                }else{
                    binding.checkboxAge.isChecked = false
                    binding.checkboxAlarm.isChecked = false
                    binding.checkboxProfile.isChecked = false
                    binding.checkboxPromise.isChecked = false
                }
            }
            checkboxAllFlag = true
            checkCheckBox()
        }

        val checkedChangeListener = object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                checkboxAllFlag = false
                if (!isChecked) {
                    binding.checkboxAll.isChecked = false
                }
                if (checkCheckBox() && binding.checkboxAlarm.isChecked) {
                    binding.checkboxAll.isChecked = true
                }
            }
        }

        binding.checkboxAge.setOnCheckedChangeListener(checkedChangeListener)
        binding.checkboxPromise.setOnCheckedChangeListener(checkedChangeListener)
        binding.checkboxProfile.setOnCheckedChangeListener(checkedChangeListener)
        binding.checkboxAlarm.setOnCheckedChangeListener(checkedChangeListener)

        binding.btnRecommender.setOnClickListener {
            showCustomToast("유효한 코드가 아닙니다.")
        }

        binding.btnSignup.setOnClickListener {
            val flag1 = checkEmail()
            val flag2 = checkPassword()
            val flag3 = checkPasswordEquals()
            val flag4 = checkNickName()
            val flag5= checkCheckBox()
            if(flag1 && flag2 && flag3 && flag4 && flag5){
                //val intent = Intent(this, CustomRecommendInfoActivity::class.java)
                //startActivity(intent)
                val postRequest = PostSignUpRequest(binding.editEmail.text.toString(),
                    binding.editPassword.text.toString(),
                    binding.editNickname.text.toString(),
                    "Y",
                    "Y",
                    "Y",
                    transCheckedToXY(binding.checkboxAlarm.isChecked))
                showLoadingDialog(this)
                SignupOneService(this).tryPostSignUp(postRequest)
            }else{
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    @SuppressLint("ResourceAsColor")
    fun checkEmail():Boolean{
        var email = binding.editEmail.text.toString().trim()
        val p = Pattern.matches(emailValidation, email)
        if (p) {
            binding.linearEmail.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_stroke_textview))
            binding.tvEmailWarning.visibility = View.GONE
            binding.btnEmailCheck.setBackground(ContextCompat.getDrawable(this, R.drawable.main_stroke_btn))
            binding.btnEmailCheck.setTextColor(Color.parseColor("#7adbfe"))
            binding.btnEmailCheck.isClickable = true
            //중복체크API
            return true
        } else {
            binding.linearEmail.setBackground(ContextCompat.getDrawable(this, R.drawable.red_stroke_textview))
            binding.tvEmailWarning.visibility = View.VISIBLE
            binding.btnEmailCheck.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_solied_btn))
            binding.btnEmailCheck.setTextColor(Color.parseColor("#686868"))
            binding.btnEmailCheck.isClickable = false
            return false
        }
    }

    fun checkPassword():Boolean{
        var pwd = binding.editPassword.text.toString().trim()
        val p = Pattern.matches(pwdValidation, pwd)
        if (p) {
            binding.linearPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_stroke_textview))
            binding.tvPasswordWarning.visibility = View.GONE
            return true
        } else {
            binding.linearPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.red_stroke_textview))
            binding.tvPasswordWarning.visibility = View.VISIBLE
            return false
        }
    }

    fun checkPasswordEquals():Boolean{
        var pwd = binding.editPassword.text.toString().trim()
        var pwdchk = binding.editPasswordCheck.text.toString().trim()
        if (pwd.equals(pwdchk)) {
            binding.linearPasswordCheck.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_stroke_textview))
            binding.tvPasswordCheckWarning.visibility = View.GONE
            return true
        } else {
            binding.linearPasswordCheck.setBackground(ContextCompat.getDrawable(this, R.drawable.red_stroke_textview))
            binding.tvPasswordCheckWarning.visibility = View.VISIBLE
            return false
        }
    }

    fun checkNickName():Boolean{
        var nick = binding.editNickname.text.toString().trim()

        if (nick.length >= 2) {
            binding.linearNickname.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_stroke_textview))
            binding.tvNicknameWarning.visibility = View.GONE
            //중복체크API
            //중복체크 걸리면 새로운 닉네임 추천
            return true
        } else {
            binding.linearNickname.setBackground(ContextCompat.getDrawable(this, R.drawable.red_stroke_textview))
            binding.tvNicknameWarning.visibility = View.VISIBLE
            return false
        }
    }

    fun checkCheckBox():Boolean{
        if(binding.checkboxAge.isChecked &&
            binding.checkboxProfile.isChecked &&
            binding.checkboxPromise.isChecked){
            binding.linearCheckbox.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_stroke_textview))
            binding.tvCheckboxWarning.visibility = View.GONE
            return true
        } else{
            binding.linearCheckbox.setBackground(ContextCompat.getDrawable(this, R.drawable.red_stroke_textview))
            binding.tvCheckboxWarning.visibility = View.VISIBLE
            return false
        }
    }

    fun transCheckedToXY(checked: Boolean) : String {
        if(checked){
            return "Y"
        }else{
            return "N"
        }
    }

    override fun onPostSignUpSuccess(response: BaseResponse) {
        response.message?.let { showCustomToast(it) }

        val getSignInRequest = GetSignInRequest(binding.editEmail.text.toString(),
            binding.editPassword.text.toString())
        SignupOneService(this).tryGetSignIn(getSignInRequest)
    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("회원가입 오류 : $message")
    }

    override fun onGetSignInSuccess(response: GetSignInResponse) {
        dismissLoadingDialog()
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt)
        editor.commit()
        Log.d("GetSign","jwt : ${ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null)}")
        Log.d("GetSign","성공 여부 : ${response.isSuccess}")
        Log.d("GetSign","메세지 : ${response.message}")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onGetSignInFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("로그인 오류 : $message")
    }
}