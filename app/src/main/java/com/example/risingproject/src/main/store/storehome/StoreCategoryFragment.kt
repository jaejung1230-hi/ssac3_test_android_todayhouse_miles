package com.example.risingproject.src.main.store.storehome

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.risingproject.R
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentStoreCategoryBinding
import com.example.risingproject.src.main.store.storehome.StoreCategoryFragment.FilterBoolean.arr
import com.example.risingproject.src.main.store.storehome.models.*
import com.example.risingproject.src.main.store.storehome.util.*
import com.example.risingproject.src.search.SearchActivity
import java.lang.Integer.min
import kotlin.properties.Delegates
import android.widget.LinearLayout
import androidx.core.view.ViewCompat.canScrollVertically

import androidx.recyclerview.widget.GridLayoutManager
import android.widget.Toast

class StoreCategoryFragment :  BaseFragment<FragmentStoreCategoryBinding>(FragmentStoreCategoryBinding::bind, R.layout.fragment_store_category) , StoreCategoryFragmentView, FilterItemClick {
    object FilterBoolean{
        var arr = arrayListOf<ArrayList<Boolean>>()
    }

    var pageNo = 1
    var pageRow = 2
    var categoryNum by Delegates.notNull<Int>()
    var underarr = listOf<InfoForUnderFilter>()
    var arr2 = listOf<String>()
    var chiparr = arrayListOf<ChipWithPos>()

    var abslist : List<Int> = listOf(R.drawable.store_category_ad1, R.drawable.store_category_ad2, R.drawable.store_category_ad3,
        R.drawable.store_category_ad4, R.drawable.store_category_ad5, R.drawable.store_category_ad6)

    private var myHandler = MyHandler()
    private var currentPosition = Int.MAX_VALUE / 2 - abslist.size / 2

    lateinit var storeArr : List<temp>


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_and_basket, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when(item?.itemId){
            R.id.home ->{
                activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
                return super.onOptionsItemSelected(item)
            }

            R.id.action_search -> {
                val intent = Intent(requireContext(),SearchActivity::class.java)
                intent.putExtra("pageNo",4)
                requireContext().startActivity(intent)
                activity?.overridePendingTransition(R.anim.slide_top_come, R.anim.slide_top_gone)
                return  super.onOptionsItemSelected(item)
            }
            R.id.action_basket -> {

                return super.onOptionsItemSelected(item)
            }
            else  ->{
                activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
                return super.onOptionsItemSelected(item)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarStoreCategory)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        arr = arrayListOf<ArrayList<Boolean>>()

        binding.containerStoreCategory.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        val bundle = arguments
        if(bundle != null){
            val name = bundle.getString("category")
            binding.toolbarCategoryTitle.text = name
        }

        if(binding.toolbarCategoryTitle.text.equals("가구")){
            categoryNum = 1

            storeArr = listOf<temp>(
                temp(R.drawable.img_store_category_furniture_sofa,"소파/거실가구","소파,침대..."),
                temp(R.drawable.img_store_category_furniture_bed,"침실가구","침구,커튼..."),
                temp(R.drawable.img_store_category_furniture_dress,"드레스룸","스탠드,무드등..."),
                temp(R.drawable.img_store_category_furniture_kitchen,"주방가구","냉장고,노트북..."),
                temp(R.drawable.img_store_category_furniture_desk,"학생/서재가구","그릇,냄비..."),
                temp(R.drawable.img_store_category_furniture_kid,"유아동가구","그림,캔들..."),
                temp(R.drawable.img_store_category_furniture_storage,"수납가구","리빙박스,행거..."),
                temp(R.drawable.img_store_category_furniture_mirror,"화장대/거울","욕실,청소..."),
                temp(R.drawable.img_store_category_furniture_table,"식탁/테이블","세제,샴푸..."),
                temp(R.drawable.img_store_category_furniture_chair,"의자/스톨","시트지,손잡이..."),
                temp(R.drawable.img_store_category_furniture_wall,"병풍/파티션","주방,욕실..."))

            arr2 = listOf<String>("정렬","리퍼 상품","색상","사용 인원","소재","우드톤", "지금 가장 핫한 상품", "자재 등급","미드센추리 모던","브랜드","조립 여부","상품 유형","가격(원)","배송")
            underarr = listOf<InfoForUnderFilter>(
                InfoForUnderFilter("radio", listOf<String>("판매순","인기순","낮은 가격순","높은 가격순","리뷰 많은 순", "유저사진 많은 순", "최신순")),
                InfoForUnderFilter("check", listOf<String>("리퍼 상품 보기")),
                InfoForUnderFilter("color", listOf<String>("화이트","그레이","블랙","베이지","브라운", "실버", "골드", "레드", "오렌지", "옐로우", "그린", "블루", "네이비", "바이올렛", "핑크", "멀티(혼합)",  "투명")),
                InfoForUnderFilter("check", listOf<String>("1인","2인","3인","4인","5인", "6인", "7인", "8인 이상")),
                InfoForUnderFilter("check", listOf<String>("원목","가공목","천연대리석","인조대리석","세라믹","화산석","유리","철재/스틸","플라스틱","라탄","콘크리트","천연가죽","인조가죽","패브릭","스웨이드","매쉬","벨벳")),
                InfoForUnderFilter("color", listOf<String>("밝은 우드톤","중간 우드톤","어두운 우드톤")),
                InfoForUnderFilter("check", listOf<String>("의자","수납가구","테이블/식탁","거울")),
                InfoForUnderFilter("check", listOf<String>("SEO(친환경)","EO(친환경)","E1","E2")),
                InfoForUnderFilter("check", listOf<String>("체어","테이블","수납장","벽선반/월캐비닛")),
                InfoForUnderFilter("check", listOf<String>("먼데이하우스","한샘","보나애가구","삼익가구","데스커", "레이디가구", "지누스", "시디즈", "두닷", "영가구", "자모코", "잉글랜더", "리샘", "포더홈", "듀커소파", "휴도",  "메종드꼼마")),
                InfoForUnderFilter("check", listOf<String>("DIY 조립형","시공기사 조립형","완제품")),
                InfoForUnderFilter("check", listOf<String>("렌탈상품 보기","렌탈상품 제외","해외직구 보기","해외직구 제외")),
                InfoForUnderFilter("radio", listOf<String>("50,000원 이하","100,000원~200,000원","200,000원~300,000원","300,000원~400,000원","400,000원~500,000원","500,000원~600,000원","600,000원~700,000원","700,000원~800,000원","800,000원~900,000원","900,000원~1,000,000원","1,000,000원 이상")),
                InfoForUnderFilter("check", listOf<String>("무료 배송","희망일배송","업체직접배송","오늘의집 배송","제주도 배송 가능"))
            )
        }else{
            categoryNum = 2

            storeArr = listOf<temp>(
                temp(R.drawable.img_store_category_frbric_bed,"침구","소파,침대..."),
                temp(R.drawable.img_store_category_frbric_blind,"커튼/블라인드","침구,커튼..."),
                temp(R.drawable.img_store_category_frbric_rug,"카페트/러그","스탠드,무드등..."),
                temp(R.drawable.img_store_category_frbric_cution,"쿠션/방석","냉장고,노트북..."),
                temp(R.drawable.img_store_category_frbric_home,"홈패브릭","그릇,냄비..."),
                temp(R.drawable.img_store_category_frbric_kitchen,"주방패브릭","그림,캔들..."),
                temp(R.drawable.img_store_category_frbric_hoby,"공예/취미","리빙박스,행거..."),
                temp(R.drawable.img_store_category_frbric_kid,"유아용패브릭","욕실,청소..."))

            arr2 = listOf<String>("정렬","사용 계절","색상","주요 소재","패턴/프린트", "지금 가장 핫한 상품", "상품 유형", "특가", "가격(원)","배송")

            underarr = listOf<InfoForUnderFilter>(
                InfoForUnderFilter("radio", listOf<String>("판매순","인기순","낮은 가격순","높은 가격순","리뷰 많은 순", "유저사진 많은 순", "최신순")),
                InfoForUnderFilter("check", listOf<String>("봄/가을(간절기)","여름","겨울","사계절")),
                InfoForUnderFilter("color", listOf<String>("화이트","그레이","블랙","베이지","브라운", "실버", "골드", "레드", "오렌지", "옐로우", "그린", "블루", "네이비", "바이올렛", "핑크", "멀티(혼합)",  "투명")),
                InfoForUnderFilter("check", listOf<String>("순면","면혼방","마/린넨","벨벳/벨루아","쉬폰", "실크", "울/양모", "극세사", "레이스", "메쉬",
                    "폴리에스테르", "폴리프로필렌", "나일론", "아크릴", "플리스", "레이온/인견",  "텐셀", "모달", "모피/퍼", "천연가죽", "인조가죽", "실리콘")),
                InfoForUnderFilter("check", listOf<String>("무지","스트라이프","실사 프린팅","도트","체크", "헤링본", "페르시안", "플라워", "에스닉", "페이즐리", "트로피컬", "레터링", "카모플라쥬", "기하학", "캐릭터", "젖소",  "지브라","호피/레오파드","뱀피/스네이크")),
                InfoForUnderFilter("check", listOf<String>("유저들의 선택")),
                InfoForUnderFilter("check", listOf<String>("해외직구 보기","해외직구 제외")),
                InfoForUnderFilter("check", listOf<String>("마텔라","플로라침구","데코뷰","그래이불","베이직톤", "한빛카페트", "바자르", "헬로우슬립", "바이빔", "아엠홈", "까르데코", "심플먼트", "에이트룸", "스타일링홈", "타카타카", "아이르",  "창안애", "라뽐므",  "보웰")),
                InfoForUnderFilter("check", listOf<String>("특가상품 보기")),
                InfoForUnderFilter("radio", listOf<String>("50,000원 이하","100,000원~200,000원","200,000원~300,000원","300,000원~400,000원","400,000원~500,000원","500,000원~600,000원","600,000원~700,000원","700,000원~800,000원","800,000원~900,000원","900,000원~1,000,000원","1,000,000원 이상")),
                InfoForUnderFilter("check", listOf<String>("무료 배송","희망일배송","업체직접배송","제주도 배송 가능"))
            )
        }

        binding.gridviewStorecategoryCategory.adapter = StoreCategoryCategoryGridViewAdapter(requireContext(),storeArr)
        binding.gridviewStorecategoryCategory.isExpanded = true


        StoreCategoryService(this).tryGetCategoryItem(GetCategoryItemRequest(categoryNum))
        val getCategoryItemUseFilterRequest = GetCategoryItemUseFilterRequest(categoryNum,null,null,null,null,null,null,null,null,null,null,pageNo,pageRow)
        StoreCategoryService(this).tryGetCategoryItemUseFilter(getCategoryItemUseFilterRequest)
        showLoadingDialog(requireContext())

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerForTenFilter.layoutManager = linearLayoutManager
        val adapter = HorizontalForTenFilterdapter(requireContext(), arr2.subList(1,min(arr2.size,11)), this)
        binding.recyclerForTenFilter.adapter = adapter
        val linearLayoutManager2 = LinearLayoutManager(requireContext())
        linearLayoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerForSelectedFilter.layoutManager = linearLayoutManager2
        val chipadapter = HorizontalForChipFilterdapter(requireContext(), chiparr, this)
        binding.recyclerForSelectedFilter.adapter = chipadapter

        binding.btnOpenDrawer.setOnClickListener {  customPenDrawer(-1)}
        binding.drawer2.btnDrawerClose.setOnClickListener { binding.containerStoreCategory.closeDrawer(Gravity.RIGHT) }
        binding.btnStickyOpenDrawer.setOnClickListener { customPenDrawer(-1) }
        binding.viewpagerStorcategoryFragment.adapter = AdsViewPagerAdapter(abslist)
        binding.viewpagerStorcategoryFragment.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpagerStorcategoryFragment.setCurrentItem(currentPosition, false) // 현재 위치를 지정
        binding.tvViewpagerStorcategoryFragmentTotalnum.text = abslist.size.toString()
        binding.viewpagerStorcategoryFragment.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvViewpagerStorcategoryFragmentNownum.text = (position%abslist.size+1).toString()
                }
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저에서 손 떼었을때 / 뷰페이저 멈춰있을 때
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(2000.toLong())
                        // 뷰페이저 움직이는 중
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }

        for(i in underarr.listIterator()){
            val arr2 = arrayListOf<Boolean>()
            for(j in i.filters.listIterator()){
                arr2.add(false)
            }
            FilterBoolean.arr.add(arr2)
        }

        for(i in FilterBoolean.arr){
            Log.d("for",i.size.toString())
        }

        val titleList = listOf<TextView>(binding.filterTitle1,binding.filterTitle2,binding.filterTitle3,binding.filterTitle4)
        val recyclerList = listOf<RecyclerView>(binding.filterRecycler1,binding.filterRecycler2,binding.filterRecycler3,binding.filterRecycler4)

        for (i in 1..4){
            titleList[i-1].text = arr2[i]
            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            recyclerList[i-1].layoutManager = linearLayoutManager
            if(arr2[i].equals("색상")) {
                val adapter = HorizontalFilterColorAdapter(requireContext(), underarr[i].filters, this,i)
                recyclerList[i-1].adapter = adapter
            }else{
                val adapter = HorizontalTextColorAdapter(requireContext(), underarr[i].filters, this,i)
                recyclerList[i-1].adapter = adapter
            }
        }

        binding.stickyScrollView.run {
            header = binding.containerSticky
            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }

        binding.gridviewStorecategoryFilter.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    var ripperCode = 0
                    if(arr2.indexOf("리퍼 상품") != -1){
                        ripperCode = if(FilterBoolean.arr[arr2.indexOf("리퍼 상품")][0]){ 1 }else{ 0 }
                    }


                    var colorFlag = false
                    var colorFlagCount = 0
                    for(i in FilterBoolean.arr[arr2.indexOf("색상")]){
                        if(i){
                            colorFlag = true
                            break
                        }
                        colorFlagCount++
                    }
                    var colorCode : String? = null
                    if(colorFlag){
                        when(underarr[arr2.indexOf("색상")].filters[colorFlagCount]){
                            "화이트" -> {colorCode = "WHITE"}
                            "그린" -> {colorCode = "GREEN"}
                            "베이지" -> {colorCode = "BEIGE"}
                            else -> {colorCode = "ETC"}
                        }
                    }
                    pageNo++
                    val getCategoryItemUseFilterRequest = GetCategoryItemUseFilterRequest(2,null,null,ripperCode,colorCode,null,null,null,null,null,null, pageNo, pageRow)
                    StoreCategoryService(this@StoreCategoryFragment).tryGetCategoryItemUseFilter(getCategoryItemUseFilterRequest)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() // 화면에 보이는 마지막 아이템의 position
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1 // 어댑터에 등록된 아이템의 총 개수 -1

                if(binding.gridviewStorecategoryFilter.canScrollVertically(1)){

                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        autoScrollStart(2000.toLong())
    }

    override fun onPause() {
        super.onPause()
        autoScrollStop()
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
                binding.viewpagerStorcategoryFragment.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(2000.toLong()) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    override fun onGetCategoryItemSuccess(response: GetCategoryItemResponse) {
        Log.d("test",response.toString())

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerDiscountEvent.layoutManager = linearLayoutManager
        binding.recyclerDiscountEvent.adapter = StoreCategoryHorizontalAdapter(requireContext(),response.result)
    }

    override fun onGetCategoryItemFailure(message: String) {
        Log.d("test",message)
    }

    lateinit var madapter : StoreCategoryCategoryFilterGridViewAdapter
    override fun onGetCategoryItemUseFilterSuccess(response: GetCategoryItemUseFilterResponse) {
        dismissLoadingDialog()
        Log.d("test1",response.toString())

        if(pageNo == 1){
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            binding.gridviewStorecategoryFilter.layoutManager = gridLayoutManager
            madapter = StoreCategoryCategoryFilterGridViewAdapter(requireContext(),response.result)
            binding.gridviewStorecategoryFilter.adapter = madapter
        }else{
            madapter.add(response.result)
        }
    }

    override fun onGetCategoryItemUseFilterFailure(message: String) {
        Log.d("test",message)
        dismissLoadingDialog()
    }

    override fun getSelectedItem(pos1: Int, pos2: Int) {
        //showCustomToast("${pos1} / ${pos2}")

        if(FilterBoolean.arr[pos1][pos2]){
            chiparr.add(ChipWithPos(underarr[pos1].filters[pos2],pos1,pos2))
            binding.recyclerForSelectedFilter.adapter!!.notifyDataSetChanged()
        }else{
            val pos = chiparr.indexOf(ChipWithPos(underarr[pos1].filters[pos2], pos1, pos2))
            chiparr.removeAt(pos)
            binding.recyclerForSelectedFilter.adapter!!.notifyItemRemoved(pos)
            binding.recyclerForSelectedFilter.adapter!!.notifyItemRangeChanged(pos, chiparr.size)
        }

        var count1 = 0
        val recyclerList = listOf<RecyclerView>(binding.filterRecycler1,binding.filterRecycler2,binding.filterRecycler3,binding.filterRecycler4)
        if(pos1 in 1..4){
            recyclerList[pos1-1].adapter!!.notifyItemChanged(pos2)
        }
        binding.recyclerForTenFilter.adapter!!.notifyItemChanged(pos1-1)
        Log.d("for","//////////////////////////////////////")
        for(i in FilterBoolean.arr){
            var count2 = 0
            for(j in i){
                if(j){
                    Log.d("for",underarr[count1].filters[count2])
                }
                count2++
            }
            count1++
        }
        Log.d("for","//////////////////////////////////////")

        var ripperCode = 0
        if(arr2.indexOf("리퍼 상품") != -1){
            ripperCode = if(FilterBoolean.arr[arr2.indexOf("리퍼 상품")][0]){ 1 }else{ 0 }
        }


        var colorFlag = false
        var colorFlagCount = 0
        for(i in FilterBoolean.arr[arr2.indexOf("색상")]){
            if(i){
                colorFlag = true
                break
            }
            colorFlagCount++
        }
        var colorCode : String? = null
        if(colorFlag){
            when(underarr[arr2.indexOf("색상")].filters[colorFlagCount]){
                "화이트" -> {colorCode = "WHITE"}
                "그린" -> {colorCode = "GREEN"}
                "베이지" -> {colorCode = "BEIGE"}
                else -> {colorCode = "ETC"}
            }
        }
        pageNo = 1
        val getCategoryItemUseFilterRequest = GetCategoryItemUseFilterRequest(2,null,null,ripperCode,colorCode,null,null,null,null,null,null, pageNo, pageRow)
        StoreCategoryService(this).tryGetCategoryItemUseFilter(getCategoryItemUseFilterRequest)
        showLoadingDialog(requireContext())
    }

    override fun getDrawer(pos1: Int) {
        customPenDrawer(pos1)
    }

    fun customPenDrawer(pageNum : Int){
        binding.drawer2.filterCategoryTop.layoutManager = LinearLayoutManager(requireContext())
        val adapter = FilterTopAdapter(requireContext(),arr2,underarr, this, pageNum)
        binding.drawer2.filterCategoryTop.adapter = adapter
        binding.containerStoreCategory.openDrawer(Gravity.RIGHT)
    }
}