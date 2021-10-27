package com.example.risingproject.src.main.store.storehome

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.risingproject.R
import com.example.risingproject.config.ApplicationClass
import com.example.risingproject.config.ApplicationClass.Companion.LOG_IN_USER
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentStoreHomeBinding
import com.example.risingproject.src.main.store.storehome.models.GetAllItemResponse
import com.example.risingproject.src.main.store.storehome.models.ResultItemAll
import com.example.risingproject.src.main.store.storehome.models.temp
import com.example.risingproject.src.main.store.storehome.util.*
import com.example.risingproject.util.GlobalFunctions


class StoreHomeFragemt : BaseFragment<FragmentStoreHomeBinding>(FragmentStoreHomeBinding::bind, R.layout.fragment_store_home), StoreHomeFragmentView {

    private var currentPosition = Int.MAX_VALUE/2
    private var myHandler = MyHandler()
    private val intervalTime = 10000.toLong()

    val adarr = listOf<Int>(R.drawable.store_home_ad1,R.drawable.store_home_ad2,R.drawable.store_home_ad3,
        R.drawable.store_home_ad4,R.drawable.store_home_ad5,R.drawable.store_home_ad6,R.drawable.store_home_ad7)

    val storeArr = listOf<temp>(temp(R.drawable.img_store_category_furniture,"가구","소파,침대..."),
        temp(R.drawable.img_store_category_fabric,"패브릭","침구,커튼..."),
        temp(R.drawable.img_store_category_lighting,"조명","스탠드,무드등..."),
        temp(R.drawable.img_store_category_electronic,"가전","냉장고,노트북..."),
        temp(R.drawable.img_store_category_kitchenware,"주방용품","그릇,냄비..."),
        temp(R.drawable.img_store_category_decoration,"데코/취미","그림,캔들..."),
        temp(R.drawable.img_store_category_storage,"수납/정리","리빙박스,행거..."),
        temp(R.drawable.img_store_category_dailysupplies,"생활용품","욕실,청소..."),
        temp(R.drawable.img_store_category_necessaries,"생필품","세제,샴푸..."),
        temp(R.drawable.img_store_category_tool,"공구/DIY","시트지,손잡이..."),
        temp(R.drawable.img_store_category_interior,"인테리어/시공","주방,욕실..."),
        temp(R.drawable.img_store_category_pets,"반려동물","사료,패션..."),
        temp(R.drawable.img_store_category_camping,"캠핑용품","텐트,테이블..."),
        temp(R.drawable.img_store_category_workout,"실내운동","요가,헬스..."),
        temp(R.drawable.img_store_category_layette,"유아/아동","침대,매트..."),
        temp(R.drawable.img_store_category_rental,"렌탈","정수기,비데...")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewpagerStorehomeFragment.adapter = AdsViewPagerAdapter(adarr)
        binding.viewpagerStorehomeFragment.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpagerStorehomeFragment.setCurrentItem(currentPosition, false)
        binding.viewpagerStorehomeFragment.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

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

        binding.gridviewStorehomeCategory.adapter = StoreHomeCategoryGridViewAdapter(requireContext(),this,storeArr)
        binding.gridviewStorehomeCategory.isExpanded = true


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
                binding.viewpagerStorehomeFragment.setCurrentItem(currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    // 다른 페이지 갔다가 돌아오면 다시 스크롤 시작
    override fun onResume() {
        super.onResume()
        StoreHomeService(this).tryGetCategoryItem()
        autoScrollStart(intervalTime)
    }

    // 다른 페이지로 떠나있는 동안 스크롤이 동작할 필요는 없음. 정지
    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

    override fun onGetAllItemSuccess(response: GetAllItemResponse) {
        Log.d("test1",response.toString())
        binding.recyclerTodaysDeal.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TodaysDealAdapter(requireContext(),response.result.subList(0,4))
        binding.recyclerTodaysDeal.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val records = GlobalFunctions.getRecordPref()
        Log.d("record","${records}")
        if (records != null) {
            val recordList = arrayListOf<ResultItemAll>()
            for(record in records){
                for(resp in response.result){
                    Log.d("record","${record.toInt()} - ${resp.itemId}")
                    if(record.toInt() == resp.itemId){
                        recordList.add(resp)
                    }
                }
            }
            recordList.reverse()
            binding.recyclerRecord.layoutManager = linearLayoutManager
            binding.recyclerRecord.adapter = StoreHomeRecordAdapter(requireContext(),recordList)
        }

        binding.tvNickname.text = "김재정"

        val recomendLinearLayoutManager = LinearLayoutManager(requireContext())
        recomendLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerRecomend.layoutManager = recomendLinearLayoutManager
        binding.recyclerRecomend.adapter = StoreHomeRecordAdapter(requireContext(),response.result)

        binding.gridviewStorehomePopulor.adapter = StoreHomePopulorFilterGridViewAdapter(requireContext(),response.result)
        binding.gridviewStorehomePopulor.isExpanded = true

    }

    override fun onGetAllItemFailure(message: String) {
        showCustomToast("데이터 불러오기에 실패했습니다.")
    }

}