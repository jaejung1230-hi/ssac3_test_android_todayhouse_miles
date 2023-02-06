package com.example.risingproject.src.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivitySplashBinding
import com.example.risingproject.src.login.LoginActivity
import com.example.risingproject.src.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val jwtToken: String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, null)
            if (jwtToken == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
                //startActivity(Intent(this, MainActivity::class.java))
            }

            finish()
        }, 1500)
    }
}