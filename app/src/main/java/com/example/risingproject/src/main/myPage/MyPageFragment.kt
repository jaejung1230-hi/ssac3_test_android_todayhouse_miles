package com.example.risingproject.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.risingproject.R
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentMyPageBinding
import com.example.risingproject.src.login.LoginActivity
import com.example.risingproject.src.main.home.homemain.util.HomeAdsViewPagerAdapter
import com.example.risingproject.src.myReview.MyReviewActivity
import com.example.risingproject.src.uploadpic.util.HomeMainService


class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    private var mCount = 0


    var abslist : List<Int> = listOf(R.drawable.store_category_ad1, R.drawable.store_category_ad2, R.drawable.store_category_ad3,
        R.drawable.store_category_ad4, R.drawable.store_category_ad5, R.drawable.store_category_ad6)

    private var myHandler = MyHandler()
    private var currentPosition = Int.MAX_VALUE / 2 - abslist.size / 2
    private val intervalTime = 10000.toLong()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_setting_and_share_and_basket, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString(ApplicationClass.X_ACCESS_TOKEN, null)
        editor.commit()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        context?.startActivity(intent)
        ActivityCompat.finishAffinity(requireActivity())
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMypage)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.linearMyReview.setOnClickListener {
            val intent = Intent(requireContext(), MyReviewActivity::class.java)
            context?.startActivity(intent)
        }

        binding.viewpagerHomemainFragment.adapter = HomeAdsViewPagerAdapter(abslist)
        binding.viewpagerHomemainFragment.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpagerHomemainFragment.setCurrentItem(currentPosition, false)
        binding.tvViewpagerHomemainFragmentTotalnum.text = abslist.size.toString()
        binding.viewpagerHomemainFragment.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvViewpagerHomemainFragmentNownum.text = (position%abslist.size+1).toString()
                }
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저에서 손 떼었을때 / 뷰페이저 멈춰있을 때
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
                        // 뷰페이저 움직이는 중
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }
    }

    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개 ... n개 만큼 계속 늘어남
        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
    }

    private fun autoScrollStop(){
        myHandler.removeMessages(0) // 핸들러를 중지시킴
    }

    private inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if(msg.what == 0) {
                ++currentPosition
                binding.viewpagerHomemainFragment.setCurrentItem(currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    override fun onResume() {
        super.onResume()
        autoScrollStart(intervalTime)
    }

    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }
}