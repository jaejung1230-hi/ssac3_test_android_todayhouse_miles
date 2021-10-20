package com.example.risingproject.src.search

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.risingproject.R
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivitySearchBinding
import com.example.risingproject.src.search.util.SearchActivityViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {
    val ListCategory = listOf<String>("통합","사진","집들이","노하우","스토어","질문과답변","전문가","유저")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectPageNo = intent.getIntExtra("pageNo",0)

        binding.viewpagerSearchFragment.apply {
            adapter = SearchActivityViewPagerAdapter(context as FragmentActivity)
        }

        TabLayoutMediator(binding.tabsSearchFragment,binding.viewpagerSearchFragment){ tab, position ->
            tab.text = ListCategory[position]
        }.attach()
        binding.tabsSearchFragment.getTabAt(selectPageNo)?.select()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_bottom_come, R.anim.slide_bottom_gone)
    }
}