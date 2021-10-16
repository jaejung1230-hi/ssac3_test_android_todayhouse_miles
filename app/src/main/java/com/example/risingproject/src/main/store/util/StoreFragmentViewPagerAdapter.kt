package com.example.risingproject.src.main.store.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.risingproject.src.main.store.storehome.StoreHomeFragemt

class StoreFragmentViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    private val NUM_PAGES = 7

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {

            return StoreHomeFragemt()

    }
}