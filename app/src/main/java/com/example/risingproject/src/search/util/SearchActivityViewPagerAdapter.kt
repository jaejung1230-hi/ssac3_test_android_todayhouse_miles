package com.example.risingproject.src.search.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.risingproject.src.main.store.storehome.StoreHomeFragemt
import com.example.risingproject.src.search.storeSearch.StoreSearchFragemt

class SearchActivityViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    private val NUM_PAGES = 8

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {

            return StoreSearchFragemt()

    }
}