package com.example.risingproject.src.signup

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.risingproject.R
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivitySignupOneBinding
import java.util.regex.Pattern


class SignupOneActivity : BaseActivity<ActivitySignupOneBinding>(ActivitySignupOneBinding::inflate) {

    val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val pwdValidation = "^[a-zA-Z0-9]*$.{8,}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarSignup)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.title = ""

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("ResourceAsColor")
    fun checkEmail():Boolean{
        var email = binding.editEmail.text.toString().trim()
        val p = Pattern.matches(emailValidation, email)
        if (p) {
            binding.linearEmail.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_stroke_textview))
            binding.tvEmailWarning.visibility = View.GONE
            binding.btnEmailCheck.setBackground(ContextCompat.getDrawable(this, R.drawable.main_stroke_btn))
            binding.btnEmailCheck.setTextColor(R.color.main)
            binding.btnEmailCheck.isClickable = true
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
        val p = Pattern.matches(emailValidation, pwd)
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
}