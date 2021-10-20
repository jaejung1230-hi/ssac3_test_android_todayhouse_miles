package com.example.risingproject.src.main

import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.risingproject.R
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivityMainBinding
import com.example.risingproject.src.main.home.HomeFragment
import com.example.risingproject.src.main.myPage.MyPageFragment
import com.example.risingproject.src.main.store.StoreFragemt
import com.example.risingproject.util.GlobalFunctions
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_store-> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, StoreFragemt())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.menu_main_btm_nav_interier -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.menu_main_btm_nav_mypage -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })

        binding.mainBtmNav.itemIconTintList = null


        val fabOff = AnimationUtils.loadAnimation(this, R.anim.floatingactionbutton_rotate_off)
        val fabOn = AnimationUtils.loadAnimation(this, R.anim.floatingactionbutton_rotate_on)

        var isOpen = true
        binding.mainFab.setOnClickListener{
            if(isOpen){
                binding.mainFab.startAnimation(fabOn)
                isOpen = false
            }else{
                binding.mainFab.startAnimation(fabOff)
                isOpen = true
            }
        }

        GlobalFunctions.setRecordPref(arrayListOf<String>("1","3","6","7","8"))
    }
}