package com.example.risingproject.src.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.risingproject.R
import com.example.risingproject.config.BaseFragment
import com.example.risingproject.databinding.FragmentHomeBinding
import com.example.risingproject.src.main.home.util.HomeFragmentViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    val ListCategory = listOf<String>("인기","팔로잉","사진","집들이","노하우","전문가집들이","질문과답변")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewpagerHomeFragment.apply {
            adapter = HomeFragmentViewPagerAdapter(context as FragmentActivity)
        }

        TabLayoutMediator(binding.tabsHomeFragment,binding.viewpagerHomeFragment){ tab, position ->
            tab.text = ListCategory[position]
        }.attach()
    }
}