package com.example.risingproject.src.main.home.homemain

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.risingproject.R
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentHomeMainBinding
import com.example.risingproject.src.main.home.homemain.models.getPicResponse
import com.example.risingproject.src.uploadpic.UploadPicActivity
import com.example.risingproject.src.main.home.homemain.models.pageIconInfo
import com.example.risingproject.src.main.home.homemain.util.BestPicRecyclerAdapter
import com.example.risingproject.src.main.home.homemain.util.HomeAdsViewPagerAdapter
import com.example.risingproject.src.main.home.homemain.util.HomePagesGridViewAdapter
import com.example.risingproject.src.uploadpic.util.HomeMainService

class HomeMainFragment : BaseFragment<FragmentHomeMainBinding>(FragmentHomeMainBinding::bind, R.layout.fragment_home_main), HomeMainFragmentView {
    private val GET_GALLERY_IMAGE = 200

    val storeArr = listOf<pageIconInfo>(pageIconInfo(R.drawable.img_home_main_shopping,"쇼핑하기",0),
        pageIconInfo(R.drawable.img_home_main_truck,"오늘의집배송",1),
        pageIconInfo(R.drawable.img_home_main_pyung,"평수별집구경",0),
        pageIconInfo(R.drawable.img_home_main_area,"공간별사진",0),
        pageIconInfo(R.drawable.img_home_main_q,"빠른시공상담",2),
        pageIconInfo(R.drawable.img_home_main_setting,"설치/수리대행",0),
        pageIconInfo(R.drawable.img_home_main_dumb,"망한 인테리어",2),
        pageIconInfo(R.drawable.img_home_main_luck,"행운 이벤트",2),

        )
    var abslist : List<Int> = listOf(R.drawable.store_category_ad1, R.drawable.store_category_ad2, R.drawable.store_category_ad3,
        R.drawable.store_category_ad4, R.drawable.store_category_ad5, R.drawable.store_category_ad6)
    private var myHandler = MyHandler()
    private var currentPosition = Int.MAX_VALUE / 2 - abslist.size / 2
    private val intervalTime = 10000.toLong()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        binding.gridviewHomemainPages.adapter = HomePagesGridViewAdapter(requireContext(), storeArr)
        binding.gridviewHomemainPages.isExpanded = true


        binding.btnUploadPicMain.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_GALLERY_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            val intent = Intent(requireContext(), UploadPicActivity::class.java)
            intent.putExtra("uri",data.data!!.toString())
            requireContext().startActivity(intent)
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
        showLoadingDialog(requireContext())
        HomeMainService(this).tryPostPic()
    }

    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

    override fun onGetPicsSuccess(response: getPicResponse) {
        Log.d("testaa",response.result.toString())
        dismissLoadingDialog()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerBestPic.layoutManager = linearLayoutManager
        binding.recyclerBestPic.adapter = BestPicRecyclerAdapter(requireContext(), response.result)
    }

    override fun onGetPicsFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}