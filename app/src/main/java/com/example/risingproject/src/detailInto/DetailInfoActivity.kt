package com.example.risingproject.src.detailInto

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.src.detailInto.models.GetDetailInfoResponse
import android.view.Menu
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.risingproject.R
import com.example.risingproject.databinding.ActivityDetailInfoBinding
import com.example.risingproject.src.allReview.AllReviewActivity
import com.example.risingproject.src.detailInto.buy.BuyBottomDialogFragment
import com.example.risingproject.src.detailInto.util.*
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemRequest
import com.example.risingproject.src.main.store.storehome.models.GetCategoryItemResponse
import com.example.risingproject.src.review.WirteReviewActivity
import com.example.risingproject.util.GlobalFunctions
import com.google.android.material.tabs.TabLayout
import java.text.DecimalFormat
import kotlin.math.roundToInt

class DetailInfoActivity : BaseActivity<ActivityDetailInfoBinding>(ActivityDetailInfoBinding::inflate), DetailInfoAcitivityView, FilterItemClick {
    object SelectedNum{
        var selectedNum = 0
    }
    val tempItmePic = listOf<Int>(R.drawable.temp_item_1, R.drawable.temp_item_2, R.drawable.temp_item_3, R.drawable.temp_item_4,
        R.drawable.temp_item_5, R.drawable.temp_item_6, R.drawable.temp_item_7)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_search_and_basket, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.tvDetailItemPriceBefore.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG

        binding.nastedStickyScrollView.run {
            header = binding.tabsDetail
            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        intent.getIntExtra("itemId",-1)
        GlobalFunctions.setRecordPref(intent.getIntExtra("itemId",-1).toString())
        DetailInfoService(this).tryGetItemSearch(intent.getIntExtra("itemId",-1))
        showLoadingDialog(this)

        binding.btnBuy.setOnClickListener {
            val locationBottomDialogFragment: BuyBottomDialogFragment = BuyBottomDialogFragment(this, intent.getIntExtra("itemId",-1))
            locationBottomDialogFragment.show(supportFragmentManager, locationBottomDialogFragment.tag)
        }

        binding.btnAllReview.setOnClickListener {
            val intent = Intent(this,AllReviewActivity::class.java)
            intent.putExtra("itemId",intent.getIntExtra("itemId",-1))
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onGetDetailInfoSuccess(response: GetDetailInfoResponse) {
        dismissLoadingDialog()
        Log.d("DetailInfo", response.result[0].photoImage.toString())
        Log.d("DetailInfo", response.result[1].itemInfo.toString())
        Log.d("DetailInfo", response.result[2].totalReivewRate.toString())
        Log.d("DetailInfo", response.result[3].totalReviewNum.toString())
        Log.d("DetailInfo", response.result[4].reviewList.toString())

        binding.viewpagerDetailItemPic.adapter = PicViewPagerAdapter(tempItmePic)
        binding.tvViewpagerViewpagerDetailItemPicTotalnum.text = tempItmePic.size.toString()
        binding.viewpagerDetailItemPic.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvViewpagerDetailItemPicNownum.text = (position+1).toString()
                }
            })
        }
        val t_dec_up = DecimalFormat("###,###,###")

        binding.tvDetailItemCompanyName.text = response.result[1].itemInfo[0].companyName
        binding.tvDetailItemCompanyName2.text = response.result[1].itemInfo[0].companyName
        binding.toolbarItemTitle.text = response.result[1].itemInfo[0].itemName
        binding.tvDetailItemName.text = response.result[1].itemInfo[0].itemName
        binding.ratingberDetailItem.rating = response.result[2].totalReivewRate[0].avgRate.roundToInt().toFloat()
        binding.tvDetailItemNumRate.text = String.format("%.1f", response.result[2].totalReivewRate[0].avgRate)
        binding.tvDetailItemCompanyPercent.text = response.result[1].itemInfo[0].percenttage.toString()+"%"
        binding.tvDetailItemPriceBefore.text = t_dec_up.format(response.result[1].itemInfo[0].price)+"원"
        binding.tvDetailItemPriceAfter.text = t_dec_up.format(response.result[1].itemInfo[0].sale)+"원"

        if(response.result[1].itemInfo[0].percenttage!! >= 50){
            binding.tvItemSpecialPrice.visibility = View.VISIBLE
        }

        binding.tvDetailItemProfit.text = response.result[1].itemInfo[0].profit
        binding.tvDetailItemDeliveryFee.text = response.result[1].itemInfo[0].deliveryFee
        binding.tvDetailItemDeliveryWay.text = response.result[1].itemInfo[0].deliveryWay
        binding.tvDetailItemExtraDeliveryFee.text = response.result[1].itemInfo[0].extraDeliveryFee
        if(response.result[1].itemInfo[0].extraDeliveryFee == null){
            binding.tvDetailItemExtraDeliveryFee.visibility = View.GONE
        }


        binding.viewpagerStyling.adapter = PicReViewPagerAdapter(this,response.result[4].reviewList)
        binding.viewpagerStyling.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpagerStyling.setClipToPadding(false)
        binding.viewpagerStyling.setPadding(60, 60, 60, 60)
        binding.viewpagerStyling.offscreenPageLimit=3
        binding.viewpagerStyling.getChildAt(0).overScrollMode=View.OVER_SCROLL_NEVER
        var transform = CompositePageTransformer()
        transform.addTransformer(MarginPageTransformer(30))


        binding.viewpagerStyling.setPageTransformer(transform)
        binding.viewpagerStyling.setCurrentItem(0, false)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerStyling.layoutManager = linearLayoutManager
        binding.recyclerStyling.adapter = HorizontalReviewsAdapter(this,response.result[4].reviewList, this)

        binding.tvReviewCount.text = response.result[3].totalReviewNum[0].reviewNum.toString()
        binding.tvItemRateBig.text =  String.format("%.1f", response.result[2].totalReivewRate[0].avgRate)
        binding.ratingbarBig.rating = response.result[2].totalReivewRate[0].avgRate.toFloat()

        binding.viewpagerStyling.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    SelectedNum.selectedNum = position
                    binding.recyclerStyling.adapter!!.notifyDataSetChanged()
                }
            })
        }

        binding.btnWriteReview.setOnClickListener {
            val intent = Intent(this, WirteReviewActivity::class.java)
            intent.putExtra("selectedItem",response.result[1].itemInfo[0])
            startActivity(intent)
        }

        var count5 = 0; var count4 = 0; var count3 = 0; var count2 = 0; var count1 = 0

        for(i in response.result[4].reviewList){
            when(i.reviewAvg.roundToInt()){
                5 -> {count5++}
                4 -> {count4++}
                3 -> {count3++}
                2 -> {count2++}
                else -> {count1++}
            }
        }

        binding.tabsDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0-> { binding.nastedStickyScrollView.scrollTo(0, binding.imgExplain.top)}
                    1-> {binding.nastedStickyScrollView.scrollTo(0, binding.linearReview.top)}
                    2-> {binding.nastedStickyScrollView.scrollTo(0, binding.linearQ.top)}
                    3-> {binding.nastedStickyScrollView.scrollTo(0, binding.linearPost.top)}
                    4-> {binding.nastedStickyScrollView.scrollTo(0, binding.linearSame.top)}
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        binding.nastedStickyScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.d("scroll", oldScrollY.toString())
            Log.d("scroll", scrollY.toString())
            Log.d("scroll",  binding.imgExplain.top.toString())
            Log.d("scroll",  binding.linearReview.top.toString())
            Log.d("scroll",  binding.linearQ.top.toString())
            Log.d("scroll",  binding.linearPost.top.toString())
            Log.d("scroll",  binding.linearSame.top.toString())

            if(scrollY == binding.linearSame.top){
                    binding.tabsDetail.getTabAt(4)?.select()
            }
            else if(scrollY == binding.linearPost.top){
                    binding.tabsDetail.getTabAt(3)?.select()
            }
            else if(scrollY == binding.linearQ.top){
                binding.tabsDetail.getTabAt(2)?.select()
            }
            else if(scrollY == binding.linearReview.top){
                binding.tabsDetail.getTabAt(1)?.select()
            }
            else if(scrollY == binding.imgExplain.top){
                binding.tabsDetail.getTabAt(0)?.select()
            }

        }
        if(count5 != 0){
            binding.tv5pointCount.text = count5.toString()
            binding.progressBar5point.progress = ((count5.toFloat()/response.result[3].totalReviewNum[0].reviewNum)*100).toInt()
        }
        if(count4 != 0){
            binding.tv4pointCount.text = count4.toString()
            binding.progressBar4point.progress = ((count4.toFloat()/response.result[3].totalReviewNum[0].reviewNum)*100).toInt()
        }
        if(count3 != 0){
            binding.tv3pointCount.text = count3.toString()
            binding.progressBar3point.progress = ((count3.toFloat()/response.result[3].totalReviewNum[0].reviewNum)*100).toInt()
        }
        if(count2 != 0){
            binding.tv2pointCount.text = count2.toString()
            binding.progressBar2point.progress = ((count2.toFloat()/response.result[3].totalReviewNum[0].reviewNum)*100).toInt()
        }
        if(count1 != 0){
            binding.tv1pointCount.text = count1.toString()
            binding.progressBar1point.progress = ((count1.toFloat()/response.result[3].totalReviewNum[0].reviewNum)*100).toInt()
        }

        if(response.result[4].reviewList.size > 3){
            binding.recyclerReviewPic.layoutManager = LinearLayoutManager(this)
            binding.recyclerReviewPic.adapter = ReviewsAdapter(this,response.result[4].reviewList.subList(0,3))
        }else{
            binding.recyclerReviewPic.layoutManager = LinearLayoutManager(this)
            binding.recyclerReviewPic.adapter = ReviewsAdapter(this,response.result[4].reviewList)
        }
        DetailInfoService(this).tryGetCategoryItem(GetCategoryItemRequest(1))

    }

    override fun onGetDetailInfoFailure(message: String) {
        Log.d("DetailInfo",message)
        dismissLoadingDialog()
    }

    override fun onGetCategoryItemSuccess(response: GetCategoryItemResponse) {
        if(response.result.size > 4){
            binding.gridviewSameCategoty.adapter = SameCategoryGridViewAdapter(this,response.result.subList(0,4))
            binding.gridviewSameCategoty.isExpanded = true

        }else{
            binding.gridviewSameCategoty.adapter = SameCategoryGridViewAdapter(this,response.result)
            binding.gridviewSameCategoty.isExpanded = true
        }
    }

    override fun onGetCategoryItemFailure(message: String) {
        Log.d("DetailInfo",message)
    }

    override fun getSelectedItem(pos: Int) {
        binding.viewpagerStyling.currentItem = pos

    }
}