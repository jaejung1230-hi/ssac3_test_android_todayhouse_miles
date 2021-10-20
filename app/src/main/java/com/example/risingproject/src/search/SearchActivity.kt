package com.example.risingproject.src.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.risingproject.R
import com.example.risingproject.config.BaseActivity
import com.example.risingproject.databinding.ActivitySearchBinding
import com.example.risingproject.src.search.storeSearch.StoreSearchFragment
import com.example.risingproject.src.search.util.SearchActivityViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {
    val ListCategory = listOf<String>("통합","사진","집들이","노하우","스토어","질문과답변","전문가","유저")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectPageNo = intent.getIntExtra("pageNo",0)

        setSupportActionBar(binding.toolbarSearch)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewpagerSearchFragment.apply {
            adapter = SearchActivityViewPagerAdapter(context as FragmentActivity)

        }

        TabLayoutMediator(binding.tabsSearchFragment,binding.viewpagerSearchFragment){ tab, position ->
            tab.text = ListCategory[position]
        }.attach()

        binding.tabsSearchFragment.getTabAt(selectPageNo)?.select()

        binding.btnToolbarSearch.setOnClickListener {
            if(binding.editToolbarSearch.text.isNotEmpty()){
                var currentFragment : StoreSearchFragment = supportFragmentManager.findFragmentByTag("f" + binding.viewpagerSearchFragment.currentItem) as StoreSearchFragment
                Log.d("Search",currentFragment.toString())
                currentFragment.startSearch(binding.editToolbarSearch.text.toString())
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_bottom_come, R.anim.slide_bottom_gone)
    }
}